import sys
import time

from datetime import datetime
from pathlib import Path
import imageio

from pypylon import pylon
from pypylon import genicam


base_dir = Path(
    "/run/user/1000/gvfs/smb-share:server=teamdfs,share=teamschnorrer/EqpSchnorrer/Nuno/FlyDome/Grab_test"
)

def get_cameras():

    transport_layer = pylon.TlFactory.GetInstance()
    device_infos = transport_layer.EnumerateDevices()
    cameras = {
        ci.GetSerialNumber(): pylon.InstantCamera(
            transport_layer.CreateDevice(ci))
    for ci in device_infos
    }
    return cameras

def grab_image(serial, camera):
    now = datetime.now().strftime('%Y-%m-%d-%H-%M-%S')
    fname = f"img_{serial}_{now}.tiff"
    print(f"saving {fname} for camera {serial}")
    camera.Open()
    try:
        camera.StartGrabbing() 
        result = camera.RetrieveResult(5000, pylon.TimeoutHandling_ThrowException)
        if result.GrabSucceeded():
            if serial == "22475435":
                imageio.imsave(base_dir/ "female" /fname, result.Array)
            else: imageio.imsave(base_dir/ "male" /fname, result.Array)
    finally:
        camera.Close()
        
def grab_all_cameras():
    cameras = get_cameras()
    for serial, camera in cameras.items():
        grab_image(serial, camera)
    
def run_every(minutes):
    while True:
        grab_all_cameras()
        time.sleep(int(minutes*60))

run_every(float(sys.argv[1]))

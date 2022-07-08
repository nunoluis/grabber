import sys
import time
import os

from datetime import datetime
from pathlib import Path
import imageio

from pypylon import pylon
from pypylon import genicam


base_dir = Path(
    "/home/nunoluis/Desktop/20220620_Luminy_Flydome/"
)

#introduce here Date Of Birth for start of experiment
dob = datetime.strptime("2022-06-20", "%Y-%m-%d")

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
    dif = datetime.now() - dob
    days_elapsed = dif.days
    print(f"saving {fname} for day {days_elapsed} for camera {serial}")
    camera.Open()
    try:
        camera.StartGrabbing() 
        result = camera.RetrieveResult(5000, pylon.TimeoutHandling_ThrowException)
        if result.GrabSucceeded():
            if serial == "22475435":
                base_dir_fem_day = f"female/day_{days_elapsed}/"
                if not os.path.exists(base_dir/base_dir_fem_day):
                    os.makedirs(base_dir/base_dir_fem_day)
                imageio.imsave(base_dir/base_dir_fem_day/fname, result.Array)
            else:
                base_dir_mal_day = f"male/day_{days_elapsed}/"
                if not os.path.exists(base_dir/base_dir_mal_day):
                    os.makedirs(base_dir/base_dir_mal_day)
                imageio.imsave(base_dir/base_dir_mal_day/fname, result.Array)
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

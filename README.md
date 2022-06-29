# grabber

## grabs single images from Pylon cameras using the pypylon API fom Basler
https://github.com/basler/pypylon

First check conda is installed and your path

`$ conda -V`
`conda 4.7`

create a Conda environment with Python 3.7 (to see available versions of Python type `conda search "^python$"` and press Enter):

`conda create -n <your environment name> python=3.7`

you can then enter the environment by typing

`conda activate <your environment name>`

(a more detailed step by step can be found here https://uoa-eresearch.github.io/eresearch-cookbook/recipe/2014/11/20/conda/)

Inside your new conda environment, install pypylon:

download the corresponding wheel to your python version:

`wget https://github.com/basler/pypylon/releases/download/1.5.1/pypylon-1.5.1-cp37-cp37m-linux_x86_64.whl`

install with pip

`pip install pypylon-1.5.1-cp37-cp37m-linux_x86_64.whl`

I had some troubles after install Pylon6, after installing pypylon I ran this inside the conda env:

`python -m pip install pypylon --upgrade` it changed the version from 1.5.1 to 1.8.0

still have issues related to libpython3.7m not found

UPDATED 2022.06.29:
Create a conda env with latest python 3.10 `conda create -n Flydome python=3.10`

install latest pypylon wheel, numpy, image and upyter notebook `pip3 install pypylon`
`pip3 install numpy`
`pip3 install imageio`
`pip3 install jupyter notebook`

from here, running `from pypylon import pylon` inside a jupyter notebook threw no errors


for this script to work it depends on a few more packages which you need to install in this conda environment. This can be done via pip. Simply type in your command line (once in your dedicated conda environment):

`pip install numpy`

and

`pip install imageio`


This is all you need.

## How to use grabber

This script will identify Basler cameras that are connected to the computer, assign the name from each to a variable "serial", which will then also be written to the file name. It writes to a specific directory in "base_dir" which you can change.

To specify the intervals at which grab an image, this can be written when loading the script from the command line in minutes. For example, to collect an image from each camera every 1 minute type

`python grab.py 1`

to collect an image from each camera every 6 seconds type

`python grab.py 0.1`


this script will keep running in the background until stopped (ctrl + Z).


you can leave your conda environment by typing `conda deactivate`

## repeat.sh

This was an attempt to use cron jobs to run the script every time (without the time intervals specified with python). It does not seem to work. To re-visit.

## Flydome_stabilize_crop_MIN_project_subfolders.java

Macro, in Fiji (imagej macro) to go over all subfolders in folder, applies a stabilization algorithm (by Kang Li and Steven Kang https://imagej.net/Image_Stabilizer) to each stack of images from same 24h, then AVERAGE projection of all images from same day - stored in same folder - and return a tiff file saved on output Directory with name of original folder.
There are crop settings (the rectangle selection to be made initially) which are specific for any experiment. This must be done for each new camera set up for any given experiment (also keep in mind that the cameras might move slightly during the length of the entire lifespan).


## classifier_macro

This short Fiji macro (ImageJ macro language) applies a Weka classifier - that has already been trained - to a folder with projections from each day, in order to identify the single flies on the bottom of the floor. For each image analized it shows the number of identified objects (particle analyzer result, in "summarize") and saves the mask used for quantification with the same name of the original file in a output Directory chosen by the user.


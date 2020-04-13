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

Macro, in Fiji (imagej macro) to go over all subfolders in folder, to AVERAGE projection of all images from same day - stored in same folder - and return a tiff file saved on output Directory with name of original folder.


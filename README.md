# grabber

## grabs single images from Pylon cameras using the pypylon API fom Basler
https://github.com/basler/pypylon

### First check conda is installed and your path

`$ conda -V`
`conda 4.7`

### create a Conda environment with Python 3.7 (to see available versions of Python type `conda search "^python$` and press Enter):

`conda create -n <your environment name> python=3.7`

you can then enter the environment by typing

`conda activate <your environment name>`

(a more detailed step by step can be found here https://uoa-eresearch.github.io/eresearch-cookbook/recipe/2014/11/20/conda/)

for this script to work it depends on a few more packages which you need to install in this conda environment. This can be done via pip. Simply type in your command line (once in your dedicated conda environment):

`pip install numpy`

and

`pip install imageio`


This is all you need.

## How to use grabber

This script will identify Basler cameras that are connected to the computer, assign the name from each to a variable "serial", which will then also be written to the file name. It writes to a specific directory in "base_dir" which you can change.

To specify the intervals at which grab an image, this can be written when loading the script from the command line in minutes. For example, to collect an image from each camera every 1 minute type

`python grab.py 1`

the script will keep running in the background.

you can leave your conda environment by typing `conda deactivate`

## repeat.sh

This was an attempt to use cron jobs to run the script every time (without the time intervals specified with python). It does not seem to work. To re-visit.

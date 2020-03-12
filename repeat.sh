#! /bin/sh
eval "$(conda shell.bash hook)"
conda activate pylon
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/nunoluis/anaconda2/envs/pylon/lib/
python grab.py

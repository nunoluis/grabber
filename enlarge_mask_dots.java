@File(style="directory", label="images to analyse") dir

setBatchMode(true);

list = getFileList(dir);
File.makeDirectory(File.getParent(dir) + File.separator + 'Normalized');

for (a=0; a<list.length; a++) {
	if (endsWith(list[a], "/"))  {
		image_diff = getFileList(dir + File.separator + list[a]);
		output_dir = File.getParent(dir) + File.separator + 'Normalized' +
		File.separator + list[a];
		File.makeDirectory (output_dir);
		for (j = 0; j < (image_diff.length); j++) {
			path1 = dir + File.separator + list[a] + image_diff[j];
			open(path1);
			title = getTitle();
			roiManager("reset");
			run("Clear Results");
			d = 8; //diameter of circle in pixels
			run("Analyze Particles...", "add");
			particles = roiManager("count");
				for (i=0; i < particles; i++) {
				roiManager("Select", i);
				List.setMeasurements;
				x = List.getValue("X");
				y = List.getValue("Y");
				run("Specify...", "width=[d] height=[d] x=[x] y=[y] oval centered");
				setColor(255);
				fill();
  				}
			roiManager("Show None");
			run("Flatten");
			roiManager("reset");
			saveAs("tiff", output_dir + title);
			run("Close All");
			}
		}

	else {}
}

setBatchMode(false);

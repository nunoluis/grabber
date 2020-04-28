@File(style="directory", label="images to analyse") inputfolder

setBatchMode(true);
list = getFileList(inputfolder);
for (a=0; a<list.length; a++)  {
	if (endsWith(list[a], "/"))  {

image_diff = getFileList(inputfolder + File.separator + list[a]);
day = list[a];
//start analysis no difference

for (j = 0; j < (image_diff.length); j++) {   //subtract from list_length the step size in path2 below 
						//to stop the loop before returning error

		path1 = inputfolder + File.separator + list[a] + File.separator + image_diff[j];
 		open(path1);
 		rename(day + '_no_diff_' + j);
 		run("Analyze Particles...", "size=0-Infinity display summarize");
		run("Close All"); 
		 }

//start difference loop 30min

//outputDiff = inputfolder + File.separator + 'diff30min'
//File.makeDirectory(outputDiff);

for (j = 0; j < (image_diff.length-1); j++) {   //subtract from list_length the step size in path2 below 
						//to stop the loop before returning error
	 	
 		path1 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j];
 		path2 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+1]; 	
 		open(path1);
 		open(path2);
 		name = day + '_diff30min_' + j;
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		//title = getTitle();
		//saveAs("Tiff", outputDiff + File.separator + title);
		run("Close All"); 
		 }
		 

//start difference loop 1 hour

//outputDiff = inputfolder + File.separator + 'diff_1h'
//File.makeDirectory(outputDiff);

for (j = 0; j < (image_diff.length-2); j++) {   //subtract from list_length the step size in path2 below 
						//to stop the loop before returning error
	 	
 		path1 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j];
 		path2 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+1];
 		path3 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+2]; 	
 		open(path1);
 		open(path2);
 		open(path3);
 		name = day + '_diff1h_' + j;
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		//title = getTitle();
		//saveAs("Tiff", outputDiff + File.separator + title);
		run("Close All"); 
		 }
		 
//start difference loop 2 hours

//outputDiff = inputfolder + File.separator + 'diff_2h'
//File.makeDirectory(outputDiff);

for (j = 0; j < (image_diff.length-4); j++) {   //subtract from list_length the step size in path2 below 
						//to stop the loop before returning error
	 	
 		path1 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j];
 		path2 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+1];
 		path3 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+2];
 		path4 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+3];
 		path5 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+4];
 		open(path1);
 		open(path2);
 		open(path3);
 		open(path4);
 		open(path5);
 		name = day + '_diff2h_' + j;
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		//title = getTitle();
		//saveAs("Tiff", outputDiff + File.separator + title);
		run("Close All"); 
		 }
		 
//start difference loop 4 hours

//outputDiff = inputfolder + File.separator + 'diff_4h'
//File.makeDirectory(outputDiff);

for (j = 0; j < (image_diff.length-8); j++) {   //subtract from list_length the step size in path2 below 
						//to stop the loop before returning error
	 	
 		path1 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j];
 		path2 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+1];
 		path3 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+2];
 		path4 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+3];
 		path5 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+4];
 		path6 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+5];
 		path7 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+6];
 		path8 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+7];
 		path9 = inputfolder + File.separator + list[a] + File.separator  + image_diff[j+8];
 		open(path1);
 		open(path2);
 		open(path3);
 		open(path4);
 		open(path5);
 		open(path6);
 		open(path7);
 		open(path8);
 		open(path9);
 		name = day + '_diff4h_' + j;
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		//title = getTitle();
		//saveAs("Tiff", outputDiff + File.separator + title);
		run("Close All"); 
		 }

}

}

IJ.renameResults("Results");
saveAs("Results", inputfolder + File.separator + 'summary_all_in_interval.ods');

setBatchMode(false);

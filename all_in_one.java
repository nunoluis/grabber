run("Close All");

//choose the trained classifier, input files directory and where to place the analized data
#@File(style="file", label="Classifier file") classifier
#@File(style="directory", label="images to test") inputfolder
	
images = getFileList(inputfolder);

outputDir = inputfolder + File.separator + 'cropped'
File.makeDirectory(outputDir);

setBatchMode(true);
	for (l=0; l<images.length; l++) {

		path = inputfolder + File.separator + images[l];
		open(path);
		makeRectangle(394, 526, 1346, 1362); //crop for Flydome A
		run("Crop");
		title = getTitle();
		saveAs("Tiff", outputDir + File.separator + title );
		run("Close All"); 
}

wait(2000);
setBatchMode(false);
	for (l=0; l<1; l++) {

		path = inputfolder + File.separator + images[l]; //consider removing this line
		open(path);
		
}
wait(1000);

//start the Weka Segmentation tool and load the classifier
run("Trainable Weka Segmentation");
wait( 3000 ); 
selectWindow("Trainable Weka Segmentation v3.2.34");
call("trainableSegmentation.Weka_Segmentation.loadClassifier", classifier);

setBatchMode(true);
image_crop = getFileList(outputDir);

outputfolder = inputfolder + File.separator + 'masks'
File.makeDirectory(outputfolder);

//start analysis loop
	for (i=0; i<image_crop.length; i++) {

		
		selectWindow("Trainable Weka Segmentation v3.2.34");
		call("trainableSegmentation.Weka_Segmentation.applyClassifier", outputDir, image_crop[i], "showResults=true", "storeResults=false", "probabilityMaps=false", "");

		selectWindow("Classification result");
		run("8-bit");
		setSlice(1);
		setAutoThreshold("Default");
		run("Convert to Mask");
		output_path = outputfolder + File.separator + image_crop[i];
		saveAs("Tiff", output_path);
		title_analized = getTitle();
		//run("Analyze Particles...", "size=8-Infinity display summarize");
		//wait(1000);
		selectWindow(title_analized);
		run("Close");
		selectWindow(image_crop[i]);
		run("Close");
		 
		
}

run("Close All");

wait( 3000 );

//start difference loop

image_diff = getFileList(outputfolder);

outputDiff = inputfolder + File.separator + 'diff'
File.makeDirectory(outputDiff);

for (j = 0; j < image_diff.length; j++) {
		
 		path1 = outputfolder + File.separator + image_diff[j];
 		path2 = outputfolder + File.separator + image_diff[j+1];
 		open(path1);
 		open(path2);
 		name = image_diff[j];
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		title = getTitle();
		saveAs("Tiff", outputDiff + File.separator + title + '_diff');
		run("Close All"); 
		 }


setBatchMode(false);

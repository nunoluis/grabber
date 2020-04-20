run("Close All");

//choose the trained classifier, input files directory and where to place the analized data
#@File(style="file", label="Classifier file") classifier
#@File(style="directory", label="images to test") inputfolder
#@File(style="directory", label="output folder for Masks") outputfolder
#@File(style="directory", label="folder for cropped images") outputDir
#@File(style="directory", label="output folder for Diff") outputDiff
	//classifier = File.openDialog("Choose the Weka trained classifier");
	//inputfolder = getDirectory("select the folder with images to test");
	//outputfolder = getDirectory("Choose an Output Directory for Masks");
	//outputfolder = "/home/nunoluis/Desktop/test_output/" this is just for debugging
	//outputDir = getDirectory("Choose a Directory for Croped images");
	//outputDiff = getDirectory("Choose a Directory for Diff images");

images = getFileList(inputfolder);

setBatchMode(true);
	for (l=0; l<images.length; l++) {

		path = inputfolder + images[l];
		open(path);
		makeRectangle(394, 526, 1346, 1362); //crop for Flydome A
		run("Crop");
		title = getTitle();
		saveAs("Tiff", outputDir + title );
		run("Close All"); 
}


//start the Weka Segmentation tool and load the classifier
run("Trainable Weka Segmentation");
wait( 3000 ); 
selectWindow("Trainable Weka Segmentation v3.2.34");
call("trainableSegmentation.Weka_Segmentation.loadClassifier", classifier);

image_crop = getFileList(outputDir);

//start analysis loop
	for (i=0; i<image_crop.length; i++) {

		
		selectWindow("Trainable Weka Segmentation v3.2.34");
		call("trainableSegmentation.Weka_Segmentation.applyClassifier", outputDir, image_crop[i], "showResults=true", "storeResults=false", "probabilityMaps=false", "");

		selectWindow("Classification result");
		run("8-bit");
		setSlice(1);
		setAutoThreshold("Default");
		run("Convert to Mask");
		output_path = outputfolder + image_crop[i];
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


for (j = 0; j < image_diff.length; j++) {
		
 		path1 = outputfolder + image_diff[j];
 		path2 = outputfolder + image_diff[j+1];
 		open(path1);
 		open(path2);
 		name = image_diff[j];
        run("Images to Stack", "name=name title=[] use");
		run("Z Project...", "projection=[Min Intensity]");
		run("Analyze Particles...", "size=0-Infinity display summarize");
		title = getTitle();
		saveAs("Tiff", outputDiff + title + '_diff');
		run("Close All"); 
		 }


setBatchMode(false);

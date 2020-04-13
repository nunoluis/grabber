run("Close All");

//choose the trained classifier, input files directory and where to place the analized data
	classifier = File.openDialog("Choose the Weka trained classifier");
	//classifier = "/home/nunoluis/Desktop/classifier_test_B31.model" this is just for debugging
	inputfolder = getDirectory("select the folder with images to test");
	outputfolder = getDirectory("Choose an Output Directory");
	//outputfolder = "/home/nunoluis/Desktop/test_output/" this is just for debugging
	images = getFileList(inputfolder);
	
//start the Weka Segmentation tool and load the classifier
run("Trainable Weka Segmentation");
wait( 3000 ); 
selectWindow("Trainable Weka Segmentation v3.2.34");
call("trainableSegmentation.Weka_Segmentation.loadClassifier", classifier);

setBatchMode(true);
//start analysis loop
	for (i=0; i<images.length; i++) {

		selectWindow("Trainable Weka Segmentation v3.2.34");
		//call("trainableSegmentation.Weka_Segmentation.applyClassifier", "/home/nunoluis/Desktop/test/", "AVG_day31.tif", "showResults=true", "storeResults=false", "probabilityMaps=false", "");
		call("trainableSegmentation.Weka_Segmentation.applyClassifier", inputfolder, images[i], "showResults=true", "storeResults=false", "probabilityMaps=false", "");

		selectWindow("Classification result");
		run("8-bit");
		setSlice(1);
		setAutoThreshold("Default");
		run("Convert to Mask");
		output_path = outputfolder + images[i];
		saveAs("Tiff", output_path);
		title_analized = getTitle();
		run("Analyze Particles...", "size=8-Infinity display summarize");
		wait(1000);
		selectWindow(title_analized);
		run("Close");
		selectWindow(images[i]);
		run("Close");
		
}
setBatchMode(false);

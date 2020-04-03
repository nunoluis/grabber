////
inputDir = getDirectory("choose the input directory");
outputDir = getDirectory("choose the output directory")
listdir = getFileList( inputDir );
for (i = 0; i < listdir.length; i++) {
        path = inputDir + listdir[i];
        if ( File.isDirectory(  path  )  ) {
                run("Image Sequence...", "open=" + path + " sort");
                if (nSlices > 1) {
                run("Image Stabilizer", "transformation=Translation maximum_pyramid_levels=1 template_update_coefficient=0.90 maximum_iterations=200 error_tolerance=0.0000001");
                //uncomment one of the lines below
		//makeRectangle(370, 516, 1346, 1362); //crop for Flydome A (2048 x 2048 images)
                makeRectangle(465, 180, 1003, 996); //crop for Flydome B (1900 x 1200 images)
		run("Crop");
		run("Z Project...", "projection=[Average Intensity]");
		title = getTitle();
		saveAs("Tiff", outputDir + title );
		run("Close All"); 
		 }

				 else {
				 	//uncomment one of the lines below
					//makeRectangle(370, 516, 1346, 1362); //crop for Flydome A (2048 x 2048 images)
		                	makeRectangle(465, 180, 1003, 996); //crop for Flydome B (1900 x 1200 images)
					run("Crop");
					title = getTitle();
					saveAs("Tiff", outputDir + title );
					run("Close All"); 
				 }
        }
}


////

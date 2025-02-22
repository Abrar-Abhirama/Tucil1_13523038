String[][] block90 = Block.rotateBlock90(matrixblock);
String[][] block180 = Block.rotateBlock180(matrixblock);
String[][] block270 = Block.rotateBlock270(matrixblock);
String[][] blockMirror = Block.mirrorVertical(matrixblock);
String[][] blockMirror90 = Block.mirrorVertical(block90);
String[][] blockMirror180 = Block.mirrorVertical(block180);
String[][] blockMirror270 = Block.mirrorVertical(block270);
allShape.add(matrixblock);


Block.printMatrix(block90);
Block.printMatrix(block180);
Block.printMatrix(block270);
Block.printMatrix(blockMirror);
Block.printMatrix(blockMirror90);
Block.printMatrix(blockMirror180);
Block.printMatrix(blockMirror270);
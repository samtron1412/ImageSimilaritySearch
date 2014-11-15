package image.similarity.search.contour;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvContour;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class Contour {

  public static Map<String, Object> imageToContour(String srcPath) {
    // image source
    IplImage src = cvLoadImage(srcPath);

    // image convert image to gray image
    IplImage grayImage = IplImage.create(src.width(), src.height(),
        IPL_DEPTH_8U, 1);
    cvCvtColor(src, grayImage, CV_BGR2GRAY);

    // modify gray image with threshold
//     cvThreshold(grayImage, grayImage, 10, 255, CV_THRESH_BINARY);
    cvAdaptiveThreshold(grayImage, grayImage, 255,
        CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, 15, 0);

    // create new memory to store cvseq
    CvMemStorage memory = CvMemStorage.create();

    // create contours sequence
    CvSeq contours = new CvSeq();

    // find image contour tree
    cvFindContours(grayImage, memory, contours, Loader.sizeof(CvContour.class),
        CV_RETR_EXTERNAL, CV_CHAIN_APPROX_SIMPLE, cvPoint(0, 0));

    // Find largest contour of image
    CvSeq largestContour = new CvSeq();
    double largestArea = 0;
    while (contours != null && !contours.isNull()) {
      double area = cvContourArea(contours);
      if (area > 0) {
        if (area > largestArea) {
          largestArea = area;
          largestContour = contours;
        }
      }
      contours = contours.h_next();
    }
    System.out.println("Largest Area: " + largestArea);
    System.out.println("Largest Contours: " + largestContour.total());

    cvDrawContours(src, largestContour, CvScalar.YELLOW, CvScalar.RED, -1, 1,
        CV_AA);
    
    File dstFile = new File(srcPath);
    String dstPath = "Images\\dst\\" + dstFile.getName();
    cvSaveImage(dstPath, src);
    
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("path", dstPath);
    result.put("contour", largestContour);

    return result;
  }
}

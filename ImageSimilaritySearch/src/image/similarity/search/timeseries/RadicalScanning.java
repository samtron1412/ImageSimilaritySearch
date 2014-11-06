package image.similarity.search.timeseries;

import java.util.ArrayList;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core.CvContour;
import org.bytedeco.javacpp.opencv_core.CvMemStorage;
import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class RadicalScanning {

  public static float[] imageToContour(String srcPath) {
    // image source
    IplImage src = cvLoadImage(srcPath);

    // image convert image to gray image
    IplImage grayImage = IplImage.create(src.width(), src.height(),
        IPL_DEPTH_8U, 1);
    cvCvtColor(src, grayImage, CV_BGR2GRAY);

    // modify gray image with threshold
    cvThreshold(grayImage, grayImage, 10, 255, CV_THRESH_BINARY);

    // create new memory to store svseq
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
//      System.out.println(contours.total());
//      System.out.println("Area: " + area);
//      System.out.println("Largest Area: " + largestArea);
      if (area > 0) {
        if (area > largestArea) {
          largestArea = area;
          largestContour = contours;
        }
      }
      contours = contours.h_next();
    }

    cvDrawContours(src, largestContour, CvScalar.YELLOW, CvScalar.RED, -1, 1, CV_AA);

//    System.out.println(largestContour.total());

    // find center point of points
    CvRect rect = cvBoundingRect(largestContour, 0);
    CvPoint centerPoint = new CvPoint();
    centerPoint.x(rect.x() + rect.width() / 2);
    centerPoint.y(rect.y() + rect.height() / 2);
    cvDrawCircle(src, centerPoint, 5, CvScalar.RED, -1, 8, 0);

   
    ArrayList<Double> euclideanDistance = new ArrayList<Double>();
    for (int j = 0; j < largestContour.total(); j++) {
      CvPoint currentPoint = new CvPoint(cvGetSeqElem(largestContour,j));
      euclideanDistance.add(Math.sqrt(Math.pow(currentPoint.x()-centerPoint.x(), 2) + Math.pow(currentPoint.y()-centerPoint.y(), 2)));
    }
//    System.out.println(euclideanDistance);
    
    float[] target = new float[euclideanDistance.size()];
    for (int i = 0; i < target.length; i++) {
       target[i] = euclideanDistance.get(i).floatValue();       
    } 
    
    String[] pathArr = srcPath.split("/");

    String targetPath = "Images\\" + pathArr[pathArr.length-1];
    cvSaveImage(targetPath, src);

//    System.exit(0);
    return target;
  }
  public static void main(String[] args) {
    imageToContour("Images\\skull.jpg");
  }

}

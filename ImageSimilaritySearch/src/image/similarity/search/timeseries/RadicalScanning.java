package image.similarity.search.timeseries;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvSeq;
import org.bytedeco.javacpp.opencv_core.IplImage;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class RadicalScanning {

  public static float[] contourToTimeSeries(Map<String, Object> input) {
    String srcPath = (String) input.get("path");
    CvSeq largestContour = (CvSeq) input.get("contour");
    
    // image source
    IplImage src = cvLoadImage(srcPath);

    // find center point of points
    CvRect rect = cvBoundingRect(largestContour, 0);
    CvPoint centerPoint = new CvPoint();
    centerPoint.x(rect.x() + rect.width() / 2);
    centerPoint.y(rect.y() + rect.height() / 2);
    cvDrawCircle(src, centerPoint, 5, CvScalar.RED, -1, 8, 0);

    // find array store distance value between center point and current point
    ArrayList<Double> euclideanDistance = new ArrayList<Double>();
    for (int j = 0; j < largestContour.total(); j++) {
      CvPoint currentPoint = new CvPoint(cvGetSeqElem(largestContour, j));
      euclideanDistance.add(Math.sqrt(Math.pow(
          currentPoint.x() - centerPoint.x(), 2)
          + Math.pow(currentPoint.y() - centerPoint.y(), 2)));
    }
    // System.out.println(euclideanDistance);

    // distance is an array store distance value
    float[] distance = new float[euclideanDistance.size()];
    for (int i = 0; i < distance.length; i++) {
      distance[i] = euclideanDistance.get(i).floatValue();
    }

    File dstFile = new File(srcPath);
    String dstPath = "Images\\dst\\" + dstFile.getName();
    cvSaveImage(dstPath, src);

    return distance;
  }
}

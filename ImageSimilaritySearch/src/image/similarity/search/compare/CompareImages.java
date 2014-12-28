package image.similarity.search.compare;

import image.similarity.search.contour.Contour;
import image.similarity.search.distance.DynamicTimeWarping;
import image.similarity.search.timeseries.RadicalScanning;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CompareImages {
  /**
   * Compare two image
   * 
   * @param firstImagePath
   * @param secondImagePath
   * @return array of float values
   */
  public static Double compareTwoImages(String firstImagePath,
      String secondImagePath) {
    Map<String, Object> firstContourMap = Contour
        .imageToContour(firstImagePath);
    float[] firstTimeSeries = RadicalScanning
        .contourToTimeSeries(firstContourMap);

    Map<String, Object> secondContourMap = Contour
        .imageToContour(secondImagePath);
    float[] secondTimeSeries = RadicalScanning
        .contourToTimeSeries(secondContourMap);

    DynamicTimeWarping dtw = new DynamicTimeWarping(firstTimeSeries,
        secondTimeSeries);

    return dtw.getDistance();
  }
  
  /**
   * Sort of map
   * 
   * @param map
   * @return map is sorted
   */
  public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
      Map<K, V> map) {
    LinkedList<Entry<K, V>> list = new LinkedList<>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
      @Override
      public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }
    return result;
  }

  /**
   * Compare image with set of images
   * 
   * @param image
   * @param imageLst
   * @return map contain distance between images
   */
  public static LinkedHashMap<String, Double> compareWithSetOfImages(File image,
      File[] imageLst) {
    LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();

    for (File img : imageLst) {
      result.put(img.getPath(), compareTwoImages(image.getPath(), img.getPath()));
    }
    
    return (LinkedHashMap<String, Double>) sortByValue(result);
  }
}

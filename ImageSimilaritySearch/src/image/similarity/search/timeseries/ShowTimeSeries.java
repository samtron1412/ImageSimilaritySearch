package image.similarity.search.timeseries;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public class ShowTimeSeries extends JFrame {
  private XYPlot plot;
  
  public ShowTimeSeries(final String title, float[] firstDistance, float[] secondDistance) {
    super(title);
    final XYDataset dataset = createDataset(firstDistance);
    final JFreeChart chart = createChart(dataset);
    this.plot = chart.getXYPlot();
    this.plot.setDataset(2, createDataset(secondDistance));
    this.plot.setRenderer(2, new StandardXYItemRenderer());
    final ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
    chartPanel.setMouseZoomable(true, false);
    setContentPane(chartPanel);
  }
  
  public ShowTimeSeries(final String title, float[] distance) {
    super(title);
    final XYDataset dataset = createDataset(distance);
    final JFreeChart chart = createChart(dataset);
    final ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
    chartPanel.setMouseZoomable(true, false);
    setContentPane(chartPanel);
  }

  private XYDataset createDataset(float[] distance) {
    final TimeSeries series = new TimeSeries("Time Series");
    Second current = new Second();
    for (int i = 0; i < distance.length; i++) {
      try {
        series.add(current, new Double(distance[i]));
        current = (Second) current.next();
      } catch (SeriesException e) {
        System.err.println("Error adding to series");
      }
    }

    return new TimeSeriesCollection(series);
  }

  private JFreeChart createChart(final XYDataset dataset) {
    return ChartFactory.createTimeSeriesChart("Time Series Comparison", "Contour",
        "Distance", dataset, false, false, false);
  }

  public static void main(final String[] args) {
    final String title = "Time Series Management";
    float[] firstDistance = {1.23f, 2.34f, 3.34f, 4.56f, 1.0f, 6.232f};
    float[] secondeDistance = {2.23f, 1.34f, 3.34f, 6.56f, 10.0f, 16.232f};
//    final ShowTimeSeries demo = new ShowTimeSeries(title, firstDistance);
    final ShowTimeSeries demo = new ShowTimeSeries(title, firstDistance, secondeDistance);
    demo.pack();
    RefineryUtilities.positionFrameRandomly(demo);
    demo.setVisible(true);
  }
}

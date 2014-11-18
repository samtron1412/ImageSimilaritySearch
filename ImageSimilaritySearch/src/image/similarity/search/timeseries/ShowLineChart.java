package image.similarity.search.timeseries;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class ShowLineChart extends JFrame {
  public ShowLineChart(String applicationTitle, String chartTitle, float[] distance) {
    super(applicationTitle);
    JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Time Series",
        "Distance", createDataset(distance), PlotOrientation.VERTICAL, true,
        true, false);

    ChartPanel chartPanel = new ChartPanel(lineChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
    setContentPane(chartPanel);
  }

  private DefaultCategoryDataset createDataset(float[] distance) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (int i = 0; i < distance.length; i++) {
      dataset.addValue(distance[i], "distance", Integer.toString(i));
    }
    return dataset;
  }

  public static void main(String[] args) {
    float[] distance = {1.23f, 2.34f, 3.34f, 4.56f, 1.0f, 6.232f};
    ShowLineChart chart = new ShowLineChart("School Vs Years",
        "Numer of Schools vs years", distance);

    chart.pack();
    RefineryUtilities.centerFrameOnScreen(chart);
    chart.setVisible(true);
  }
}

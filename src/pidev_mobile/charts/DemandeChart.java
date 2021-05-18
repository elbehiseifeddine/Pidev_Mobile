/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.charts;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Component;

import com.codename1.charts.util.ColorUtil;
import pidev_mobile.services.StatService;
/**
 *
 * @author ahmed
 */
public class DemandeChart  extends AbstractDemoChart{    
    private XYMultipleSeriesDataset dataSet;

     /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    @Override
    public String getName() {
        return "Sales horizontal bar chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    @Override
    public String getDesc() {
        return "The monthly sales for the last 2 years (horizontal bar chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "";
    }
 private XYMultipleSeriesDataset getDataSet() {
        if(dataSet == null) {
            dataSet = createTemperatureDataset();
        }
        return dataSet;
    }
    @Override
    public Component execute() {
   int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.CYAN, ColorUtil.MAGENTA};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
            PointStyle.TRIANGLE, PointStyle.SQUARE};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(renderer, "Demande par mois", "Month", "Nombre de demande", 0.5, 12.5, 0, 32,
                ColorUtil.LTGRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Component.RIGHT);
        renderer.setYLabelsAlign(Component.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(StatService.getInstance().getStatDemandeEmploi());
        renderer.setPanEnabled(true);
        renderer.setZoomEnabled(true);
        renderer.setZoomLimits(StatService.getInstance().getStatDemandeStage());
        initRendererer(renderer);
        CubicLineChart chart = new CubicLineChart(
                getDataSet(),
                renderer,
                0.33f
        );
        ChartComponent c = newChart(chart);
        return c;

    }

}

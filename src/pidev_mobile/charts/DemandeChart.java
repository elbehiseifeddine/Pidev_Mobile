/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.charts;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import pidev_mobile.services.StatService;
/**
 *
 * @author ahmed
 */
public class DemandeChart extends AbstractDemoChart{
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

    @Override
    public Component execute() {
        String[] titles = new String[]{"Demande Emploi par mois", "Demande Stage par mois"};
        List<double[]> values = new ArrayList<>();
        values.add(StatService.getInstance().getStatDemandeEmploi());
        values.add(StatService.getInstance().getStatDemandeStage());
        int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.BLUE};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(Orientation.HORIZONTAL);
        setChartSettings(renderer, "Nombre des demandes par mois", "Month", "Nombre de demande ", 0.5,
                12.5, 0, 24000, ColorUtil.GRAY, ColorUtil.BLUE);
        renderer.setXLabels(1);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1, "Jan");
        renderer.addXTextLabel(2, "Feb");
        renderer.addXTextLabel(3, "Mar");
        renderer.addXTextLabel(4, "Apr");
        renderer.addXTextLabel(5, "May");
        renderer.addXTextLabel(6, "Jui");
        renderer.addXTextLabel(7, "Jul");
        renderer.addXTextLabel(8, "Aug");
        renderer.addXTextLabel(9, "Sep");
        renderer.addXTextLabel(10, "Oct");
        renderer.addXTextLabel(11, "Nov");
        renderer.addXTextLabel(12, "Dec");
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                Type.DEFAULT);
        return newChart(chart);

    }

}

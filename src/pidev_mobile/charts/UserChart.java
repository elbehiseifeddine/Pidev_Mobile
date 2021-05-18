/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_mobile.charts;

/**
 *
 * @author ahmed
 */
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.models.XYValueSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BubbleChart;
import com.codename1.charts.views.CombinedXYChart;
import com.codename1.charts.views.CombinedXYChart.XYCombinedChartDef;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.charts.views.LineChart;
import com.codename1.charts.views.PointStyle;
import com.codename1.ui.Component;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import pidev_mobile.services.StatService;

/**
 * Combined temperature demo chart.
 */
public class UserChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    @Override
    public String getName() {
        return "Combined temperature";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    @Override
    public String getDesc() {
        return "The average temperature in 2 Greek islands, water temperature and sun shine hours (combined chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "Weather parameters";
    }
 
    @Override
    public Component execute() {
        

        String[] titles = new String[]{"Nombre de Freelancer par mois", "Nombre de Societe par mois"};
        List<double[]> x = new ArrayList<>();
        for (String title : titles) {
            x.add(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        }
        List<double[]> values = new ArrayList<>();
        values.add(StatService.getInstance().getStatFreelancer());
        values.add(StatService.getInstance().getStatSociete());
        int[] colors = new int[]{ColorUtil.GREEN, ColorUtil.rgb(200, 150, 0)};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        renderer.setPointSize(5.5f);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            r.setLineWidth(2);
            r.setFillPoints(true);
        }
        setChartSettings(renderer, "Satestique des utilisteurs par mois", "Month", "Nombre d'utilisateur", 0.5, 12.5, 0, 40,
        ColorUtil.LTGRAY, ColorUtil.LTGRAY);

        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Component.RIGHT);
        renderer.setYLabelsAlign(Component.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(new double[]{-10, 20, -10, 40});
        renderer.setZoomLimits(new double[]{-10, 20, -10, 40});

       

        XYSeries waterSeries = new XYSeries("Nombre de freelancer par mois");
        double[] freelaner = StatService.getInstance().getStatFreelancer();
        
        for (int i = 1; i <= 12; i++) {
            waterSeries.add(i, freelaner[i-1]);
        }
        
        
        XYSeries waterSeries2 = new XYSeries("Nombre de societe par mois");
        double[] societe = StatService.getInstance().getStatSociete();
        
        for (int i = 1  ; i <= 12; i++) {
            waterSeries2.add(i, societe[i-1]);
        }
        renderer.setBarSpacing(0.3);
        XYSeriesRenderer waterRenderer1 = new XYSeriesRenderer();
        waterRenderer1.setColor(0xff0099cc);//bleu
        waterRenderer1.setChartValuesTextAlign(Component.CENTER);
        XYSeriesRenderer waterRenderer2 = new XYSeriesRenderer();
        waterRenderer2.setColor(0xff9933cc);// mauve
        waterRenderer2.setChartValuesTextAlign(Component.RIGHT);

        XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
        dataset.addSeries(0, waterSeries);
        dataset.addSeries(0, waterSeries2);
        renderer.addSeriesRenderer(0, waterRenderer1);
        renderer.addSeriesRenderer(0, waterRenderer2);
        waterRenderer1.setDisplayChartValues(true);
        waterRenderer1.setChartValuesTextSize(smallFont.getHeight() / 2);
        waterRenderer2.setDisplayChartValues(true);
        waterRenderer2.setChartValuesTextSize(smallFont.getHeight() / 2);

        XYCombinedChartDef[] types = new XYCombinedChartDef[]{
            new XYCombinedChartDef(BarChart.TYPE, 0, 1),
            new XYCombinedChartDef(LineChart.TYPE, 2), new XYCombinedChartDef(CubicLineChart.TYPE, 3)};

        initRendererer(renderer);
        CombinedXYChart chart = new CombinedXYChart(dataset, renderer, types);
        return newChart(chart);

    }

    
    
}

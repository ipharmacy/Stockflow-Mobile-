/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.swr.entities.Entrepot;

/**
 *
 * @author HP
 */
public class StatEntrepot {

    Resources theme;

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);
        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        renderer.setLabelsColor(ColorUtil.BLACK);
        for (int color : colors) {

            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Entrepot> ab) {
        CategorySeries series = new CategorySeries(title);

        for (Entrepot u : ab) {
            series.add(u.getNom(), u.getVues());
            System.out.println(u.getNom() + u.getVues());

        }

        return series;
    }

    public void createPieChartForm(String titre, ArrayList<Entrepot> ab) {
        // Generate the values

        theme = UIManager.initFirstTheme("/theme");
              
        int size = ab.size();
        System.out.println(size);
        double[] values = new double[size];
        int i = 0;


       

        for (Entrepot u : ab) {
            System.out.println(i);
            values[i] = u.getVues();
            i++;
        }

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.rgb(255, 102, 1), ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.YELLOW};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset(titre, ab), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form(new BorderLayout());
        Label logi = new Label("STATISTIQUES ");

        f.add(BorderLayout.NORTH, logi);
        f.add(BorderLayout.CENTER, c);

        f.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            new listEntrepot().show();
        });
                f.show();



    }
}

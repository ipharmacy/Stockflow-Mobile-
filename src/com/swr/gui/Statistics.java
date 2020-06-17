/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chihe
 */
public class Statistics extends BaseForm{
    Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
    Font medFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_MEDIUM, Font.STYLE_PLAIN);
    Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);
    public Statistics(Form previous,String username)
    {
        
      setTitle("Statistiques Taches : "+username);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e -> previous.showBack() ); 
      System.out.println(" Taches faites ");
      
      int nbfAvril=0;
      int nbfJanvier=0;
      int nbfFevrier=0;
      int nbfMars=0;
      int nbfMai=0;
      int nbfJuin=0;
      int nbfJuillet=0;
      int nbfAout=0;
      int nbfSeptembre=0;
      int nbfOctober=0;
      int nbfNovember=0;
      int nbfDecembre=0;
      
      int nbnfAvril=0;
      int nbnfJanvier=0;
      int nbnfFevrier=0;
      int nbnfMars=0;
      int nbnfMai=0;
      int nbnfJuin=0;
      int nbnfJuillet=0;
      int nbnfAout=0;
      int nbnfSeptembre=0;
      int nbnfOctober=0;
      int nbnfNovember=0;
      int nbnfDecembre=0;
      
      
      for (Map.Entry<String, Integer> entry : com.swr.services.ServicesTache.getInstance().TachesFaites(30).entrySet()) 
      {
          if(entry.getKey().equals("janvier"))
          {
              nbfJanvier=entry.getValue();
          }
          if(entry.getKey().equals("fevrier"))
          {
              nbfFevrier=entry.getValue();
          }
          if(entry.getKey().equals("mars"))
          {
              nbfMars=entry.getValue();
          }
          if(entry.getKey().equals("avril"))
          {
              nbfAvril=entry.getValue();
          }
          if(entry.getKey().equals("mai"))
          {
              nbfMai=entry.getValue();
          }
            
          if(entry.getKey().equals("juin"))
          {
              nbfJuin=entry.getValue();
          }
              
           if(entry.getKey().equals("juillet"))
          {
              nbfJuillet=entry.getValue();
          }
           if(entry.getKey().equals("aout"))
          {
              nbfAout=entry.getValue();
          }
           if(entry.getKey().equals("september"))
          {
              nbfSeptembre=entry.getValue();
          }
          if(entry.getKey().equals("october"))
          {
              nbfOctober=entry.getValue();
          }
          if(entry.getKey().equals("november"))
          {
              nbfNovember=entry.getValue();
          }
          if(entry.getKey().equals("december"))
          {
              nbfDecembre=entry.getValue();
          }                         
      }
      
       System.out.println(" Taches faites en mai : "+nbfMai);
        System.out.println("Taches faites en juin : "+nbfJuin);
       System.out.println(" Taches non faites ");

       for (Map.Entry<String, Integer> entry : com.swr.services.ServicesTache.getInstance().TachesNonFaites(30).entrySet()) 
        {
            if(entry.getKey().equals("janvier"))
          {
              nbnfJanvier=entry.getValue();
          }
          if(entry.getKey().equals("fevrier"))
          {
              nbnfFevrier=entry.getValue();
          }
          if(entry.getKey().equals("mars"))
          {
              nbnfMars=entry.getValue();
          }
          if(entry.getKey().equals("avril"))
          {
              nbnfAvril=entry.getValue();
          }
          if(entry.getKey().equals("mai"))
          {
              nbnfMai=entry.getValue();
          }
            
          if(entry.getKey().equals("juin"))
          {
              nbnfJuin=entry.getValue();
          }
              
           if(entry.getKey().equals("juillet"))
          {
              nbnfJuillet=entry.getValue();
          }
           if(entry.getKey().equals("aout"))
          {
              nbnfAout=entry.getValue();
          }
           if(entry.getKey().equals("september"))
          {
              nbnfSeptembre=entry.getValue();
          }
          if(entry.getKey().equals("october"))
          {
              nbnfOctober=entry.getValue();
          }
          if(entry.getKey().equals("november"))
          {
              nbnfNovember=entry.getValue();
          }
          if(entry.getKey().equals("december"))
          {
              nbnfDecembre=entry.getValue();
          }                             
        }
	
    String[] titles = new String[]{"Taches Faites", "Taches non Faites"};
    List<Integer[]> values = new ArrayList<Integer[]>();
    values.add(new Integer[]{nbfJanvier,nbfMai,nbfMars,nbfAvril,nbfMai,nbfJuin,nbfJuillet,nbfAout,nbfSeptembre,nbfOctober,
    nbfNovember,nbfDecembre});
    values.add(new Integer[]{nbfJanvier,nbfMai,nbfMars,nbnfAvril,nbnfMai,nbnfJuin,nbnfJuillet,nbnfAout,nbnfSeptembre,nbnfOctober,
    nbnfNovember,nbnfDecembre});
    int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.BLUE};
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
    setChartSettings(renderer, "Statistiques des taches faites/non faites", "mois", "nombre de taches", 0.1,
    10, 0,10, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(1);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1,"Janvier");
        renderer.addXTextLabel(3,"Mars");
        renderer.addXTextLabel(5,"Mai");
        renderer.addXTextLabel(7,"Juillet");
        renderer.addXTextLabel(9,"Septembre");
        renderer.addXTextLabel(11,"November");
        renderer.addXTextLabel(13,"Decembre");
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
         for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }
      BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
               BarChart.Type.DEFAULT);
     ChartComponent c = new ChartComponent(chart);
     this.add(c);
    }
    
     protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(smallFont.getHeight() / 2);
        renderer.setChartTitleTextFont(smallFont);
        renderer.setLabelsTextSize(smallFont.getHeight() / 2);
        renderer.setLegendTextSize(smallFont.getHeight() / 2);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

        protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
            String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
            int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }
        
         protected void initRendererer(DefaultRenderer renderer) {
        renderer.setBackgroundColor(0xffffffff);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsColor(0xff000000);
        renderer.setAxesColor(0xff000000);
        if(Font.isNativeFontSchemeSupported()) {
            Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                    derive(Display.getInstance().convertToPixels(2.5f), Font.STYLE_PLAIN);
            renderer.setTextTypeface(fnt);
            renderer.setChartTitleTextFont(fnt);
            renderer.setLabelsTextFont(fnt);
            renderer.setLegendTextFont(fnt);

            if(renderer instanceof XYMultipleSeriesRenderer) {
                ((XYMultipleSeriesRenderer)renderer).setAxisTitleTextFont(fnt);
            }
            if(renderer instanceof XYMultipleSeriesRenderer) {
                XYMultipleSeriesRenderer x = (XYMultipleSeriesRenderer)renderer;
                x.setMarginsColor(0xfff7f7f7);
                x.setXLabelsColor(0xff000000);
                x.setYLabelsColor(0, 0xff000000);
            }
        }

    }
         
         
        protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<Integer[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            Integer[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }
    
    
    
}

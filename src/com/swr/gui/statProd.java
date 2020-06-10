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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.swr.entities.SessionUser;
import com.swr.services.ServiceProduit;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Dhia
 */

public class statProd {
    
  
    
     
    public int calcnb()
    {
         ArrayList<String> noms = new ArrayList<>();
        int id = SessionUser.loggedUser.getId();
        noms=com.swr.services.ServiceProduit.getInstance().getNomStatproduits(id);
         return noms.size();
    }
     
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(40);
        renderer.setLegendTextSize(35);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        ArrayList<String> noms = new ArrayList<>();
        ArrayList<Double> nb = new ArrayList<>();
        int id = SessionUser.loggedUser.getId();
        noms=com.swr.services.ServiceProduit.getInstance().getNomStatproduits(id);
        nb=com.swr.services.ServiceProduit.getInstance().getNbStatproduits(id);
     //   String a=new String(con.getResponseData());
     
     
     for(int i=0;i<noms.size();i++)
        { 
            int value = (int)Math.round(nb.get(i));
            series.add(value+"*"+noms.get(i),nb.get(i));
            System.out.println("NOMS PROD : "+noms.get(i)+"NB PROD : "+nb.get(i));
       
        } 
       

        return series;
    }
    public Form createPieChartForm1(Resources theme) {
        // Generate the values
        double[] values = new double[]{50, 99, 11, 30, 25, 60};
        // Set up the renderer
         int[] colors = new int[20];
         int size=calcnb();
         System.out.println("TAILLE KADEH"+size+"TAILLE COLORS TAW "+colors.length);
//       
            for (int i=0;i<size;i++)
            {
                System.out.println("couleur : "+colors[i]);
                Random rand = new Random();
                int x1=  rand.nextInt(255);
                int x2 =  rand.nextInt(255);
                int x3=  rand.nextInt(255);
                int x4 =  rand.nextInt(255);
                colors[i]=ColorUtil.rgb(x1,x2,x3);
                System.out.println("couleur : "+colors[i]);
             }
        
         
     //  int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
       //ColorUtil.argb(15, 14, 200,50)};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(200);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);
            
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Pourcentages", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
        // Create a form and show it.
        Form f = new Form("Statistique");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        // menu  cc= new menu(theme);
        //Toolbar
        
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);
        return f;
    }
}
package com.vktech.statsapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BarChart barChart;
    PieChart pieChart;
    RadarChart radarChart;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Setup Toolbar ---
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // --- Find Chart Views ---
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
        radarChart = findViewById(R.id.radarChart);

        // --- Setup Charts ---
        setupBarChart();
        setupPieChart();
        setupRadarChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_support) {
            Toast.makeText(this, "Support option selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 85));
        entries.add(new BarEntry(1, 75));
        entries.add(new BarEntry(2, 90));
        entries.add(new BarEntry(3, 80));

        String[] labels = {"OOP", "DSU", "JPR", "MAD"};

        BarDataSet dataSet = new BarDataSet(entries, "Marks in Subject");
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        dataSet.setColors(colors);

        BarData data = new BarData(dataSet);
        data.setValueTextSize(12f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setTextSize(12f);

        barChart.setData(data);
        barChart.invalidate();

        Legend legend = barChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);

        ArrayList<LegendEntry> customEntries = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = labels[i];
            customEntries.add(entry);
        }
        legend.setCustom(customEntries);
    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "Homework"));
        entries.add(new PieEntry(40f, "Classwork"));
        entries.add(new PieEntry(30f, "Attendance"));

        PieDataSet dataSet = new PieDataSet(entries, "Assessment Weightage");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);

        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.getDescription().setText("Assessment Distribution");
        pieChart.invalidate();
    }

    private void setupRadarChart() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(85));
        entries.add(new RadarEntry(75));
        entries.add(new RadarEntry(90));
        entries.add(new RadarEntry(65));

        final String[] labels = {"Attack", "Defense", "Speed", "Stamina"};

        RadarDataSet dataSet = new RadarDataSet(entries, "Player Stats");
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.COLORFUL_COLORS) {
            colors.add(color);
        }
        dataSet.setColors(colors);
        dataSet.setFillColor(colors.get(0));
        dataSet.setDrawFilled(true);
        dataSet.setLineWidth(2f);

        RadarData data = new RadarData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.DKGRAY);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.BLACK);

        radarChart.setData(data);
        radarChart.animateY(1000);
        radarChart.getDescription().setText("Player Skill Distribution");
        radarChart.getYAxis().setDrawLabels(false);
        radarChart.invalidate();

        // --- Definitive Legend Fix for Overlapping ---
        Legend legend = radarChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);

        // Use a VERTICAL orientation to guarantee no overlap
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setDrawInside(false);
        legend.setYEntrySpace(5f); // Space between stacked items

        ArrayList<LegendEntry> customEntries = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = labels[i];
            customEntries.add(entry);
        }
        legend.setCustom(customEntries);
    }
}
package com.example.setupbuilder.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.R
import com.example.setupbuilder.util.XAxisValueFormatter
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.view_cpu_benchmark_activity.*


class CpuBenchmarkActivity : AppCompatActivity() {

    lateinit var benchmarkChart: HorizontalBarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_cpu_benchmark_activity)

        setCpuBenchmarkGraph()
    }

    private fun setCpuBenchmarkGraph() {
        benchmarkChart = horizontalbarchart

        benchmarkChart.setDrawBarShadow(false)
        val description = Description()
        description.text = ""
        benchmarkChart.description = description
        benchmarkChart.legend.isEnabled = false
        benchmarkChart.setPinchZoom(false)
        benchmarkChart.setDrawValueAboveBar(false)

        val xAxis = benchmarkChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setEnabled(true)
        xAxis.setDrawAxisLine(false)

        val yLeft = benchmarkChart.axisLeft
        yLeft.axisMaximum = 100f
        yLeft.axisMinimum = 0F
        yLeft.isEnabled = false

        xAxis.setLabelCount(5)

        val values = arrayOf("1*", "2*", "3*", "4*", "5*")
        xAxis.valueFormatter = XAxisValueFormatter(values)

        val yRight = benchmarkChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false

        setGraphData()

        benchmarkChart.animateY(2000)
    }

    private fun setGraphData() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 27f))
        entries.add(BarEntry(1f, 45f))
        entries.add(BarEntry(2f, 65f))
        entries.add(BarEntry(3f, 77f))
        entries.add(BarEntry(4f, 93f))

        val barDataSet = BarDataSet(entries, "Bar Data Set")

        benchmarkChart.setDrawBarShadow(true)
        barDataSet.barShadowColor = Color.argb(40, 150, 150, 150)
        val data = BarData(barDataSet)

        data.barWidth = 0.9f

        benchmarkChart.data = data
        benchmarkChart.invalidate()
    }

}

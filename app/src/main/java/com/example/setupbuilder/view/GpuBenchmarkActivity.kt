package com.example.setupbuilder.view

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.setupbuilder.R
import com.example.setupbuilder.util.RoundedBarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.gpu_filter_dialog.view.*
import kotlinx.android.synthetic.main.view_gpu_benchmark_activity.*


class GpuBenchmarkActivity : AppCompatActivity() {

    private lateinit var benchmarkChart: HorizontalBarChart
    private var vendor = "both"
    private var order = "cresc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_gpu_benchmark_activity)
        benchmarkChart = horizontal_bar_chart
        try {
            if (!this?.intent?.getStringExtra("order").isNullOrBlank()) {
                order = this?.intent?.getStringExtra("order").toString()
            }
            if (!this?.intent?.getStringExtra("vendor").isNullOrBlank()) {
                vendor = this?.intent?.getStringExtra("vendor").toString()
            }
        } catch (e: Exception) {
        }
        configuresBarChart()
    }

    override fun onStart() {
        super.onStart()

        gpu_filter.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.gpu_filter_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()
            val intent = Intent(this, GpuBenchmarkActivity::class.java)

            if (order == "cresc") {
                mDialogView.cresc_filter.isChecked = true
                intent.putExtra("order", "cresc")
            } else if (order == "decresc") {
                mDialogView.decresc_filter.isChecked = true
                intent.putExtra("order", "decresc")
            }

            if (vendor == "nvidia") {
                mDialogView.nvidia_filter.isChecked = true
                intent.putExtra("vendor", "intel")
            } else if (vendor == "amd") {
                intent.putExtra("vendor", "amd")
                mDialogView.amd_filter.isChecked = true
            } else {
                intent.putExtra("vendor", "both")
            }

            mDialogView.cresc_filter.setOnClickListener {
                intent.putExtra("order", "cresc")
                mDialogView.decresc_filter.isChecked = false
            }

            mDialogView.decresc_filter.setOnClickListener {
                intent.putExtra("order", "decresc")
                mDialogView.cresc_filter.isChecked = false
            }

            mDialogView.nvidia_filter.setOnClickListener {
                if (vendor != "nvidia") {
                    intent.putExtra("vendor", "nvidia")
                } else {
                    intent.putExtra("vendor", "both")
                    mDialogView.nvidia_filter.isChecked = false
                }
            }

            mDialogView.amd_filter.setOnClickListener {
                if (vendor != "amd") {
                    intent.putExtra("vendor", "amd")
                } else {
                    intent.putExtra("vendor", "both")
                    mDialogView.amd_filter.isChecked = false
                }
            }

            mDialogView.button_filter.setOnClickListener {

                if (!mDialogView.nvidia_filter.isChecked && !mDialogView.amd_filter.isChecked) {
                    intent.putExtra("vendor", "both")
                }

                if (!mDialogView.cresc_filter.isChecked && !mDialogView.decresc_filter.isChecked) {
                    intent.putExtra("order", "cresc")
                }

                mAlertDialog.dismiss()
                startActivity(intent)
                finish()
            }
        }
    }

    private fun configuresBarChart() {
        var barDataSet = getDataSet(vendor, order)
        setBarChartColor(barDataSet, vendor)
        barDataSet.setDrawValues(true)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.7F
        barData.setValueTextColor(Color.rgb(255, 255, 255))
        barData.setValueTextSize(16F)

        val xAxis = benchmarkChart.xAxis
        xAxis.granularity = 1F
        xAxis.labelCount = 28

        val formatter = IndexAxisValueFormatter(getXAxisValues(vendor, order))
        xAxis.valueFormatter = formatter
        xAxis.textColor = Color.rgb(255, 255, 255)
        xAxis.textSize = 16F
        xAxis.setDrawGridLines(false)
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(false)

        val yLeft = benchmarkChart.axisLeft
        yLeft.axisMaximum = 30000F
        yLeft.axisMinimum = 0F
        yLeft.textColor = Color.rgb(255, 255, 255)
        yLeft.isEnabled = false

        val yRight = benchmarkChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false

        benchmarkChart.setTouchEnabled(false)
        benchmarkChart.isDragEnabled = false
        benchmarkChart.setScaleEnabled(false)
        benchmarkChart.isScaleXEnabled = false
        benchmarkChart.isScaleYEnabled = false
        benchmarkChart.isClickable = false
        benchmarkChart.setDrawBarShadow(false)
        benchmarkChart.legend.isEnabled = false
        benchmarkChart.setPinchZoom(false)
        benchmarkChart.setDrawValueAboveBar(false)
        benchmarkChart.setFitBars(true)
        benchmarkChart.description.text = ""
        benchmarkChart.data = barData
        benchmarkChart.renderer =
            RoundedBarChart(benchmarkChart, benchmarkChart.animator, benchmarkChart.viewPortHandler)
        benchmarkChart.animateY(1000)
        benchmarkChart.invalidate()
    }

    private fun getDataSet(vendor: String, order: String): BarDataSet {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 25029f))
        entries.add(BarEntry(1f, 23882f))
        entries.add(BarEntry(2f, 21586f))
        entries.add(BarEntry(3f, 21502f))
        entries.add(BarEntry(4f, 19374f))
        entries.add(BarEntry(5f, 18607f))
        entries.add(BarEntry(6f, 18128f))
        entries.add(BarEntry(7f, 17647f))
        entries.add(BarEntry(8f, 16711f))
        entries.add(BarEntry(9f, 16381f))
        entries.add(BarEntry(10f, 16171f))
        entries.add(BarEntry(11f, 14796f))
        entries.add(BarEntry(12f, 14456f))
        entries.add(BarEntry(13f, 14053f))
        entries.add(BarEntry(14f, 14047f))
        entries.add(BarEntry(15f, 13771f))
        entries.add(BarEntry(16f, 13467f))
        entries.add(BarEntry(17f, 13309f))
        entries.add(BarEntry(18f, 12669f))
        entries.add(BarEntry(19f, 12205f))
        entries.add(BarEntry(20f, 11553f))
        entries.add(BarEntry(21f, 11066f))
        entries.add(BarEntry(22f, 10154f))
        entries.add(BarEntry(23f, 9818f))
        entries.add(BarEntry(24f, 9482f))
        entries.add(BarEntry(25f, 9220f))
        entries.add(BarEntry(26f, 8951f))
        entries.add(BarEntry(27f, 8748f))

        if (vendor == "both" && order == "decresc") {
            entries.clear()
            entries.add(BarEntry(27f, 25029f))
            entries.add(BarEntry(26f, 23882f))
            entries.add(BarEntry(25f, 21586f))
            entries.add(BarEntry(24f, 21502f))
            entries.add(BarEntry(23f, 19374f))
            entries.add(BarEntry(22f, 18607f))
            entries.add(BarEntry(21f, 18128f))
            entries.add(BarEntry(20f, 17647f))
            entries.add(BarEntry(19f, 16711f))
            entries.add(BarEntry(18f, 16381f))
            entries.add(BarEntry(17f, 16171f))
            entries.add(BarEntry(16f, 14796f))
            entries.add(BarEntry(15f, 14456f))
            entries.add(BarEntry(14f, 14053f))
            entries.add(BarEntry(13f, 14047f))
            entries.add(BarEntry(12f, 13771f))
            entries.add(BarEntry(11f, 13467f))
            entries.add(BarEntry(10f, 13309f))
            entries.add(BarEntry(9f, 12669f))
            entries.add(BarEntry(8f, 12205f))
            entries.add(BarEntry(7f, 11553f))
            entries.add(BarEntry(6f, 11066f))
            entries.add(BarEntry(5f, 10154f))
            entries.add(BarEntry(4f, 9818f))
            entries.add(BarEntry(3f, 9482f))
            entries.add(BarEntry(2f, 9220f))
            entries.add(BarEntry(1f, 8951f))
            entries.add(BarEntry(0f, 8748f))
        } else if (vendor == "nvidia") {
            entries.clear()
            benchmarkChart.layoutParams.height = 2394

            if (order == "cresc") {
                entries.add(BarEntry(0f, 25029f))
                entries.add(BarEntry(1f, 23882f))
                entries.add(BarEntry(2f, 21586f))
                entries.add(BarEntry(3f, 21502f))
                entries.add(BarEntry(4f, 19374f))
                entries.add(BarEntry(5f, 18607f))
                entries.add(BarEntry(6f, 18128f))
                entries.add(BarEntry(7f, 17647f))
                entries.add(BarEntry(8f, 16381f))
                entries.add(BarEntry(9f, 16171f))
                entries.add(BarEntry(10f, 14796f))
                entries.add(BarEntry(11f, 14053f))
                entries.add(BarEntry(12f, 14047f))
                entries.add(BarEntry(13f, 13771f))
                entries.add(BarEntry(14f, 13309f))
                entries.add(BarEntry(15f, 12669f))
                entries.add(BarEntry(16f, 12205f))
                entries.add(BarEntry(17f, 11553f))
                entries.add(BarEntry(18f, 11066f))
                entries.add(BarEntry(19f, 10154f))
                entries.add(BarEntry(20f, 9818f))
                entries.add(BarEntry(21f, 9482f))
                entries.add(BarEntry(22f, 8951f))
            } else if (order == "decresc") {
                entries.add(BarEntry(22f, 25029f))
                entries.add(BarEntry(21f, 23882f))
                entries.add(BarEntry(20f, 21586f))
                entries.add(BarEntry(19f, 21502f))
                entries.add(BarEntry(18f, 19374f))
                entries.add(BarEntry(17f, 18607f))
                entries.add(BarEntry(16f, 18128f))
                entries.add(BarEntry(15f, 17647f))
                entries.add(BarEntry(14f, 16381f))
                entries.add(BarEntry(13f, 16171f))
                entries.add(BarEntry(12f, 14796f))
                entries.add(BarEntry(11f, 14053f))
                entries.add(BarEntry(10f, 14047f))
                entries.add(BarEntry(9f, 13771f))
                entries.add(BarEntry(8f, 13309f))
                entries.add(BarEntry(7f, 12669f))
                entries.add(BarEntry(6f, 12205f))
                entries.add(BarEntry(5f, 11553f))
                entries.add(BarEntry(4f, 11066f))
                entries.add(BarEntry(3f, 10154f))
                entries.add(BarEntry(2f, 9818f))
                entries.add(BarEntry(1f, 9482f))
                entries.add(BarEntry(0f, 8951f))
            }
        } else if (vendor == "amd") {
            entries.clear()
            benchmarkChart.layoutParams.height = 600

            if (order == "cresc") {
                entries.add(BarEntry(4f, 8748f))
                entries.add(BarEntry(3f, 9220f))
                entries.add(BarEntry(2f, 13467f))
                entries.add(BarEntry(1f, 14456f))
                entries.add(BarEntry(0f, 16711f))
            } else if (order == "decresc") {
                entries.add(BarEntry(0f, 8748f))
                entries.add(BarEntry(1f, 9220f))
                entries.add(BarEntry(2f, 13467f))
                entries.add(BarEntry(3f, 14456f))
                entries.add(BarEntry(4f, 16711f))
            }
        }

        return BarDataSet(entries, "Bar Data Set")
    }

    private fun getXAxisValues(vendor: String, order: String): ArrayList<String> {
        val xAxis = ArrayList<String>()
        xAxis.add("RTX 3090")
        xAxis.add("RTX 3080")
        xAxis.add("RTX 2080 Ti")
        xAxis.add("RTX 3070")
        xAxis.add("RTX 2080 S")
        xAxis.add("RTX 2080")
        xAxis.add("RTX 2070 S")
        xAxis.add("GTX 1080 Ti")
        xAxis.add("RX 5700 XT")
        xAxis.add("RTX 2060 S")
        xAxis.add("RTX 2070")
        xAxis.add("GTX 1080")
        xAxis.add("RX 5700")
        xAxis.add("GTX 1070 Ti")
        xAxis.add("RTX 2060")
        xAxis.add("GTX 980 Ti")
        xAxis.add("RX 5600 XT")
        xAxis.add("GTX 1070")
        xAxis.add("GTX 1660 S")
        xAxis.add("GTX 1660 Ti")
        xAxis.add("GTX 1660")
        xAxis.add("GTX 980")
        xAxis.add("GTX 1060")
        xAxis.add("GTX 1650 S")
        xAxis.add("GTX 970")
        xAxis.add("RX 590")
        xAxis.add("GTX 780 Ti")
        xAxis.add("RX 580")

        if (vendor == "both" && order == "decresc") {
            xAxis.reverse()
        } else if (vendor == "nvidia") {
            xAxis.removeAll { n -> n.contains("RX") }
            if (order == "decresc") {
                xAxis.reverse()
            }
        } else if (vendor == "amd") {
            xAxis.removeAll { n -> n.contains("GTX") }
            xAxis.removeAll { n -> n.contains("RTX") }
            if (order == "decresc") {
                xAxis.reverse()
            }
        }
        return xAxis
    }

    private fun setBarChartColor(barData: BarDataSet, vendor: String) {

        when (vendor) {
            "both" -> {
                barData.setColors(
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.nvidiaGreen),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange)
                )
            }

            "nvidia" -> {
                barData.color = Color.rgb(118, 185, 0)
            }

            "amd" -> {
                barData.color = Color.rgb(237, 139, 0)
            }
        }
    }
}
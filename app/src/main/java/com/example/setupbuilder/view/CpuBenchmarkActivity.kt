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
import kotlinx.android.synthetic.main.cpu_filter_dialog.view.*
import kotlinx.android.synthetic.main.view_cpu_benchmark_activity.*


class CpuBenchmarkActivity : AppCompatActivity() {

    private lateinit var benchmarkChart: HorizontalBarChart
    private var vendor = "both"
    private var order = "cresc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_cpu_benchmark_activity)
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

        cpu_filter.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(this).inflate(R.layout.cpu_filter_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()
            val intent = Intent(this, CpuBenchmarkActivity::class.java)

            if (order == "cresc") {
                mDialogView.cresc_filter.isChecked = true
                intent.putExtra("order", "cresc")
            } else if (order == "decresc") {
                mDialogView.decresc_filter.isChecked = true
                intent.putExtra("order", "decresc")
            }

            if (vendor == "intel") {
                mDialogView.intel_filter.isChecked = true
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

            mDialogView.intel_filter.setOnClickListener {
                if(vendor != "intel") {
                    intent.putExtra("vendor", "intel")
                } else {
                    intent.putExtra("vendor", "both")
                    mDialogView.intel_filter.isChecked = false
                }
            }

            mDialogView.amd_filter.setOnClickListener {
                if(vendor != "amd") {
                    intent.putExtra("vendor", "amd")
                } else {
                    intent.putExtra("vendor", "both")
                    mDialogView.amd_filter.isChecked = false
                }
            }

            mDialogView.button_filter.setOnClickListener {

                if(!mDialogView.intel_filter.isChecked && !mDialogView.amd_filter.isChecked) {
                    intent.putExtra("vendor", "both")
                }

                if(!mDialogView.cresc_filter.isChecked && !mDialogView.decresc_filter.isChecked) {
                    intent.putExtra("order", "cresc")
                }

                mAlertDialog.dismiss()
                startActivity(intent)
                finish()
            }
        }
    }

    private fun configuresBarChart() {
        val barDataSet = getDataSet(vendor, order)
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
        yLeft.axisMaximum = 35000F
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
        entries.add(BarEntry(27f, 8983f))
        entries.add(BarEntry(26f, 9408f))
        entries.add(BarEntry(25f, 9596f))
        entries.add(BarEntry(24f, 10872f))
        entries.add(BarEntry(23f, 11760f))
        entries.add(BarEntry(22f, 12421f))
        entries.add(BarEntry(21f, 12708f))
        entries.add(BarEntry(20f, 12779f))
        entries.add(BarEntry(19f, 12786f))
        entries.add(BarEntry(18f, 13085f))
        entries.add(BarEntry(17f, 13221f))
        entries.add(BarEntry(16f, 13382f))
        entries.add(BarEntry(15f, 14075f))
        entries.add(BarEntry(14f, 14619f))
        entries.add(BarEntry(13f, 14640f))
        entries.add(BarEntry(12f, 17587f))
        entries.add(BarEntry(11f, 17859f))
        entries.add(BarEntry(10f, 18334f))
        entries.add(BarEntry(9f, 18880f))
        entries.add(BarEntry(8f, 19693f))
        entries.add(BarEntry(7f, 21164f))
        entries.add(BarEntry(6f, 21908f))
        entries.add(BarEntry(5f, 22237f))
        entries.add(BarEntry(4f, 22683f))
        entries.add(BarEntry(3f, 22822f))
        entries.add(BarEntry(2f, 23350f))
        entries.add(BarEntry(1f, 24272f))
        entries.add(BarEntry(0f, 30863f))

        if (vendor == "both" && order == "decresc") {
            entries.clear()

            entries.add(BarEntry(0f, 8983f))
            entries.add(BarEntry(1f, 9408f))
            entries.add(BarEntry(2f, 9596f))
            entries.add(BarEntry(3f, 10872f))
            entries.add(BarEntry(4f, 11760f))
            entries.add(BarEntry(5f, 12421f))
            entries.add(BarEntry(6f, 12708f))
            entries.add(BarEntry(7f, 12779f))
            entries.add(BarEntry(8f, 12786f))
            entries.add(BarEntry(9f, 13085f))
            entries.add(BarEntry(10f, 13221f))
            entries.add(BarEntry(11f, 13382f))
            entries.add(BarEntry(12f, 14075f))
            entries.add(BarEntry(13f, 14619f))
            entries.add(BarEntry(14f, 14640f))
            entries.add(BarEntry(15f, 17587f))
            entries.add(BarEntry(16f, 17859f))
            entries.add(BarEntry(17f, 18334f))
            entries.add(BarEntry(18f, 18880f))
            entries.add(BarEntry(19f, 19693f))
            entries.add(BarEntry(20f, 21164f))
            entries.add(BarEntry(21f, 21908f))
            entries.add(BarEntry(22f, 22237f))
            entries.add(BarEntry(23f, 22683f))
            entries.add(BarEntry(24f, 22822f))
            entries.add(BarEntry(25f, 23350f))
            entries.add(BarEntry(26f, 24272f))
            entries.add(BarEntry(27f, 30863f))

        } else if (vendor == "intel") {

            benchmarkChart.layoutParams.height = 1314

            entries.clear()

            if (order == "cresc") {
                entries.add(BarEntry(10f, 8983f))
                entries.add(BarEntry(9f, 9596f))
                entries.add(BarEntry(8f, 10872f))
                entries.add(BarEntry(7f, 12708f))
                entries.add(BarEntry(6f, 14619f))
                entries.add(BarEntry(5f, 14640f))
                entries.add(BarEntry(4f, 18880f))
                entries.add(BarEntry(3f, 19693f))
                entries.add(BarEntry(2f, 21908f))
                entries.add(BarEntry(1f, 22683f))
                entries.add(BarEntry(0f, 24272f))
            } else if (order == "decresc") {
                entries.add(BarEntry(0f, 8983f))
                entries.add(BarEntry(1f, 9596f))
                entries.add(BarEntry(2f, 10872f))
                entries.add(BarEntry(3f, 12708f))
                entries.add(BarEntry(4f, 14619f))
                entries.add(BarEntry(5f, 14640f))
                entries.add(BarEntry(6f, 18880f))
                entries.add(BarEntry(7f, 19693f))
                entries.add(BarEntry(8f, 21908f))
                entries.add(BarEntry(9f, 22683f))
                entries.add(BarEntry(10f, 24272f))
            }

        } else if (vendor == "amd") {

            benchmarkChart.layoutParams.height = 1964

            entries.clear()

            if (order == "cresc") {
                entries.add(BarEntry(16f, 9408f))
                entries.add(BarEntry(15f, 11760f))
                entries.add(BarEntry(14f, 12421f))
                entries.add(BarEntry(13f, 12779f))
                entries.add(BarEntry(12f, 12786f))
                entries.add(BarEntry(11f, 13085f))
                entries.add(BarEntry(10f, 13221f))
                entries.add(BarEntry(9f, 13382f))
                entries.add(BarEntry(8f, 14075f))
                entries.add(BarEntry(7f, 17587f))
                entries.add(BarEntry(6f, 17859f))
                entries.add(BarEntry(5f, 18334f))
                entries.add(BarEntry(4f, 21164f))
                entries.add(BarEntry(3f, 22237f))
                entries.add(BarEntry(2f, 22822f))
                entries.add(BarEntry(1f, 23350f))
                entries.add(BarEntry(0f, 30863f))
            } else if (order == "decresc") {
                entries.add(BarEntry(0f, 9408f))
                entries.add(BarEntry(1f, 11760f))
                entries.add(BarEntry(2f, 12421f))
                entries.add(BarEntry(3f, 12779f))
                entries.add(BarEntry(4f, 12786f))
                entries.add(BarEntry(5f, 13085f))
                entries.add(BarEntry(6f, 13221f))
                entries.add(BarEntry(7f, 13382f))
                entries.add(BarEntry(8f, 14075f))
                entries.add(BarEntry(9f, 17587f))
                entries.add(BarEntry(10f, 17859f))
                entries.add(BarEntry(11f, 18334f))
                entries.add(BarEntry(12f, 21164f))
                entries.add(BarEntry(13f, 22237f))
                entries.add(BarEntry(14f, 22822f))
                entries.add(BarEntry(15f, 23350f))
                entries.add(BarEntry(16f, 30863f))
            }
        }

        return BarDataSet(entries, "Bar Data Set")
    }

    private fun getXAxisValues(vendor: String, order: String): ArrayList<String> {
        val xAxis = ArrayList<String>()
        xAxis.add("Ryzen 3900")
        xAxis.add("i9-10900K")
        xAxis.add("Ryzen 3800X")
        xAxis.add("Ryzen 3700X")
        xAxis.add("i9-10900X")
        xAxis.add("Ryzen 5600X")
        xAxis.add("i9-9900X")
        xAxis.add("Ryzen 4700G")
        xAxis.add("i7-10700K")
        xAxis.add("i9-9900K")
        xAxis.add("Ryzen 3600X")
        xAxis.add("Ryzen 3600")
        xAxis.add("Ryzen 2700X")
        xAxis.add("i5-10600K")
        xAxis.add("i7-9700K")
        xAxis.add("Ryzen 2600X")
        xAxis.add("Ryzen 3500X")
        xAxis.add("Ryzen 2600")
        xAxis.add("Ryzen 1600X")
        xAxis.add("Ryzen 3300X")
        xAxis.add("Ryzen 3500")
        xAxis.add("i5-10400F")
        xAxis.add("Ryzen 1600")
        xAxis.add("Ryzen 3100")
        xAxis.add("i5-9600K")
        xAxis.add("i5-9400F")
        xAxis.add("Ryzen 3400G")
        xAxis.add("i3-10100")

        if (vendor == "both" && order == "decresc") {

            xAxis.reverse()

        } else if (vendor == "intel") {

            xAxis.removeAll { n -> n.contains("Ryzen") }

            if (order == "decresc") {
                xAxis.reverse()
            }

        } else if (vendor == "amd") {

            xAxis.removeAll { n -> n.contains("i") }

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
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
                    ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
                    ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange)
                )
            }
            "intel" -> {
                barData.color = Color.rgb(0, 51, 255)
            }
            "amd" -> {
                barData.color = Color.rgb(237, 139, 0)
            }
        }
    }
}
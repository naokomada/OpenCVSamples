package com.example.admin.opencvsamples

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import org.opencv.android.Utils
import org.opencv.core.Scalar
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Rect


class Sample1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        //samples
        createMat()
        importFromResource()
        trimMat()
    }

    // 単純にMatを生成して、bitmapにして表示させる
    fun createMat() {
        val mat = Mat(200, 200, CvType.CV_8UC3, Scalar(0.0, 0.0, 255.0))
        val bitmap = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, bitmap)

        val imgv = findViewById<ImageView>(R.id.imageView1)
        imgv.setImageBitmap(bitmap)
    }

    fun importFromResource()  {
        // リソースからpngを取得してbmpにする
        val sourceBmp = BitmapFactory.decodeResource(resources, R.drawable.sample2)
        // Matに変換する
        var mat = Mat()
        Utils.bitmapToMat(sourceBmp, mat, true)

        // あたらしいBmpをつくってmatを変換する
        val newBmp = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, newBmp)

        // できたBmpを表示
        val imgv = findViewById<ImageView>(R.id.imageView2)
        imgv.setImageBitmap(newBmp)
    }

    fun trimMat() {
        val mat = matFromResourceImg()

        val trimval = 200
        val roi = Rect(trimval,trimval,mat.cols()-trimval,mat.rows()-trimval)
        val newmat = Mat(mat,roi)

        setMatToImageView(newmat)
    }


    private fun matFromResourceImg(): Mat{
        // リソースからpngを取得してbmpにする
        val sourceBmp = BitmapFactory.decodeResource(resources, R.drawable.sample2)
        // Matに変換する
        var mat = Mat()
        Utils.bitmapToMat(sourceBmp, mat, true)

        return mat
    }

    private fun setMatToImageView(mat: Mat) {
        // あたらしいBmpをつくってmatを変換する
        val newBmp = Bitmap.createBitmap(mat.width(), mat.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(mat, newBmp)

        // できたBmpを表示
        val imgv = findViewById<ImageView>(R.id.imageView)
        imgv.setImageBitmap(newBmp)
    }
}

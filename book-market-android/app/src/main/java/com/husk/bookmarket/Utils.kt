package com.husk.bookmarket

import android.content.ContentResolver
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt


object Utils {
    fun processImage(
        contentResolver: ContentResolver,
        imageUri: Uri,
        targetWidth: Int,
        targetHeight: Int
    ): ByteArray {
        val bitmap = BitmapFactory.decodeStream(
            contentResolver.openInputStream(imageUri)
        )

        var width = targetWidth
        var height = targetHeight
        if (bitmap.height > 0 && bitmap.width > 0) {
            height =
                (width.toFloat() * (bitmap.height.toFloat() / bitmap.width.toFloat())).roundToInt()
        }
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
        val baos = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 60, baos)
        return baos.toByteArray()
    }

    fun readFile(resolver: ContentResolver, uri: Uri): ByteArray {
        return resolver.openInputStream(uri)!!.readBytes()
    }

    fun getFileName(resolver: ContentResolver, uri: Uri): String? {
        val returnCursor: Cursor = resolver.query(uri, null, null, null, null)!!
        val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }
}

package com.husk.bookmarket

import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.api.Context
import com.google.firebase.storage.StorageReference
import java.io.InputStream
import  com.bumptech.glide.annotation.GlideModule

@GlideModule
class AppGlide: AppGlideModule(){

    override fun registerComponents(
        context: android.content.Context,
        glide: Glide,
        registry: Registry
    ) {
        super.registerComponents(context, glide, registry)
        registry.append(
            StorageReference::class.java, InputStream::class.java,
            FirebaseImageLoader.Factory()
        )

    }
}
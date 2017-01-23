package com.ks.androidtree;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Admin on 2017/1/23 0023 11:21.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class ImageLoaderConfigurationUtil {

    public static ImageLoaderConfiguration getCustomImageLoaderConfiguration(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).memoryCacheExtraOptions(640, 640)
                // default = device screen dimensions
                .threadPoolSize(5)
                // default
                .threadPriority(Thread.MAX_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                // memoryCache
                .memoryCache(new UsingFreqLimitedMemoryCache(16 * 1024 * 1024))
                // memoryCacheSizePercentage
                .memoryCacheSizePercentage(12)
                // default
                .diskCacheSize(50 * 1024 * 1024)
                // diskCacheFileCount
                .diskCacheFileCount(100)
                // cacheFileNameGenerator
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                // logs,delete it when release
                .writeDebugLogs()
                // build
                .build();
        return config;
    }
}

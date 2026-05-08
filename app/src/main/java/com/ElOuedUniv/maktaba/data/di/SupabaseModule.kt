package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            // الرابط الصحيح هو رابط الـ API
            supabaseUrl = "https://hillqbxkzvxacvbwvydk.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhpbGxxYnhrenZ4YWN2Ynd2eWRrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgwOTMxMjIsImV4cCI6MjA5MzY2OTEyMn0.S1do9ZHFLGH4NmwhzuXvjrdDCwOOi2Tgw2GjG399x0A"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}

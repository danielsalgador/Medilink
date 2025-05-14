package com.example.proyectomedilink.interfaces

import com.example.proyectomedilink.Model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // MÉDICOS
    @GET("medicos")
    suspend fun obtenerMedicos(): List<Medico>

    @GET("medicos/{id}")
    suspend fun obtenerMedico(@Path("id") id: Long): Medico

    @POST("medicos")
    suspend fun guardarMedico(@Body medico: Medico): Medico

    @PUT("medicos/{id}")
    suspend fun actualizarMedico(
        @Path("id") id: Long,
        @Body medico: Medico
    ): Medico

    @DELETE("medicos/{id}")
    suspend fun eliminarMedico(@Path("id") id: Long)

    // PACIENTE
    @GET("pacientes")
    suspend fun obtenerPacientes(): List<Paciente>

    @GET("pacientes/{id}")
    suspend fun obtenerPaciente(@Path("id") id: Long): Paciente

    @POST("pacientes")
    suspend fun guardarPaciente(@Body paciente: Paciente): Paciente

    @PUT("pacientes/{id}")
    suspend fun actualizarPaciente(@Path("id") id: Long, @Body paciente: Paciente): Paciente

    @DELETE("pacientes/{id}")
    suspend fun eliminarPaciente(@Path("id") id: Long)


    // Citas médicas
    @GET("citas")
    suspend fun obtenerTodasCitas(): List<CitaMedica>

    @GET("citas/{id}")
    suspend fun obtenerCitaMedica(@Path("id") id: Long): CitaMedica

    @POST("citas")
    suspend fun guardarCitaMedica(@Body cita: CitaMedica): Response<CitaMedica>

    @PUT("citas/{id}")
    suspend fun actualizarCitaMedica(@Path("id") id: Long, @Body cita: CitaMedica)

    @DELETE("citas/{id}")
    suspend fun eliminarCitaMedica(@Path("id") id: Long)


    // HISTORIAS MÉDICAS
    @GET("historias")
    suspend fun obtenerHistorias(): List<HistoriaMedica>

    @GET("historias/{id}")
    suspend fun obtenerHistoriaMedica(@Path("id") id: Long): HistoriaMedica

    @POST("historias")
    suspend fun guardarHistoriaMedica(@Body historiaMedica: HistoriaMedica): HistoriaMedica

    @PUT("historias/{id}")
    suspend fun actualizarHistoriaMedica(@Path("id") id: Long, @Body historiaMedica: HistoriaMedica): HistoriaMedica

    @DELETE("historias/{id}")
    suspend fun eliminarHistoriaMedica(@Path("id") id: Long)

    // DISPONIBILIDAD HORARIA
    @GET("disponibilidad/{medicoId}")
    suspend fun obtenerDisponibilidadHoraria(@Path("medicoId") medicoId: Long): List<DisponibilidadHoraria>

    @POST("disponibilidad")
    suspend fun guardarDisponibilidadHoraria(@Body disponibilidad: DisponibilidadHoraria): DisponibilidadHoraria

    @PUT("disponibilidad/{id}")
    suspend fun actualizarDisponibilidadHoraria(@Path("id") id: Long, @Body disponibilidad: DisponibilidadHoraria): DisponibilidadHoraria

    @DELETE("disponibilidad/{id}")
    suspend fun eliminarDisponibilidadHoraria(@Path("id") id: Long)
}

// Cliente Retrofit
object RetrofitClient {
    private const val BASE_URL = "http://10.157.65.165:8080" // Cambiar según sea necesario

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

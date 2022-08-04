package com.example.searchphoto.ui.main

import app.cash.turbine.test
import com.example.searchphoto.helpers.TestDispatchers
import data.DataState
import io.mockk.*
import junitparams.JUnitParamsRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import model.SearchPhotosResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import repository.SearchPhotosRepository
import repository.SearchPhotosRepositoryImpl
import service.RetrofitConsultationService

@ExperimentalCoroutinesApi
@RunWith(JUnitParamsRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: SearchPhotosRepository
    private lateinit var consultationService: RetrofitConsultationService
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun init() {
        MockKAnnotations.init(this)
        consultationService = mockk(relaxed = true)
        repository = SearchPhotosRepositoryImpl(consultationService)

        testDispatchers = TestDispatchers()
        Dispatchers.setMain(testDispatchers.default)
        viewModel = MainViewModel(repository, testDispatchers)

    }

    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun testViewModelGetData() = runBlocking {

        val mockData = SearchPhotosResponse(
            page = 0, per_page = 0, photos = listOf(), total_results = 0, next_page = ""
        )
        coEvery { consultationService.getPhotos("cat") } returns mockData


        viewModel.getData("cat")

        viewModel.getPicturesState.test {
            val emission = awaitItem()
            assert(emission is DataState.Success)

            cancelAndConsumeRemainingEvents()
        }
    }
}
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
import room.LocalSearchPhotos
import room.PhotosDao
import service.RetrofitConsultationService
import usecase.GetPhotosUseCase

@ExperimentalCoroutinesApi
@RunWith(JUnitParamsRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var useCase: GetPhotosUseCase
    private lateinit var repository: SearchPhotosRepository
    private lateinit var consultationService: RetrofitConsultationService
    private lateinit var photosPDao: PhotosDao
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun init() {
        MockKAnnotations.init(this)
        consultationService = mockk(relaxed = true)
        photosPDao = mockk(relaxed = true)

        repository = SearchPhotosRepositoryImpl(consultationService, photosPDao)
        useCase = GetPhotosUseCase(repository)

        testDispatchers = TestDispatchers()
        Dispatchers.setMain(testDispatchers.default)

        viewModel = MainViewModel(useCase, testDispatchers)

    }

    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun testViewModelGetData() = runBlocking {

        val mockData = listOf(
            SearchPhotosResponse(
                albumId = 0,
                id = 0,
                title = " FIRST TITLE",
                url = " nop",
                thumbnailUrl = " hello"
            ),
            SearchPhotosResponse(
                albumId = 1,
                id = 2,
                title = " Secound TITLE",
                url = " Yes",
                thumbnailUrl = " Hi"
            ),
        )
        val dbMockData = listOf(
            LocalSearchPhotos(
                albumId = 0,
                id = 0,
                title = " DB FIRST TITLE",
                url = " nop",
                thumbnailUrl = " hello"
            ),
            LocalSearchPhotos(
                albumId = 1,
                id = 2,
                title = " DB Secound TITLE",
                url = " Yes",
                thumbnailUrl = " Hi"
            ),
        )

        coEvery { consultationService.getPhotos() } returns mockData
        coEvery { photosPDao.getAllPictures() } returns dbMockData


        viewModel.getData()

        viewModel.picturesState.test {
            val emission = awaitItem()
            assert(emission is DataState.Success)

            (emission as DataState.Success).data.forEachIndexed { index, elem ->
                assert(elem.title == mockData[index].title)
            }
            cancelAndConsumeRemainingEvents()
        }
    }
}
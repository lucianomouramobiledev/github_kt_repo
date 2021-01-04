package com.lucianommoura.githubktrepo

import com.lucianommoura.githubktrepo.model.GitHubItemResponse
import com.lucianommoura.githubktrepo.model.Item
import com.lucianommoura.githubktrepo.model.License
import com.lucianommoura.githubktrepo.model.Owner
import com.lucianommoura.githubktrepo.repository.GitHubRespository
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Mock
    var itemPar: Item = Item(
        full_name = "android/architecture-samples",
        owner = Owner(login = "ownerName"),
        stargazers_count = 10000,
        forks_count = 20000,
        license = License(key = "licenseKey")
    )

    @Mock
    var listOfItems: List<Item> = arrayListOf<Item>(itemPar)

    @Mock
    var mockList: ArrayList<GitHubItemResponse>? =
        arrayListOf(GitHubItemResponse(false, listOfItems, listOfItems.size))

    lateinit var mainViewModel: MainViewModel
    lateinit var gitHubRespository: GitHubRespository

    @Before
    fun inicializa() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun assertListIsEmpty() {
        mockList?.let { mockItemList ->
            mockList?.clear()
            assertEquals(mockItemList.size, 0)
        }
    }

    @Test
    fun assertListHasTwoItems() {
        var firstItem: Item = Item(
            full_name = "android/architecture-samples",
            owner = Owner(login = "ownerName"),
            stargazers_count = 10000,
            forks_count = 20000,
            license = License(key = "licenseKey")
        )
        var secondItem: Item = Item(
            full_name = "android/architecture-samples",
            owner = Owner(login = "ownerName"),
            stargazers_count = 10000,
            forks_count = 20000,
            license = License(key = "licenseKey")
        )

        var listOfItems = arrayListOf<Item>(firstItem, secondItem)
        var gitHubItemResponse = GitHubItemResponse(false, listOfItems, 2)
        assertEquals(gitHubItemResponse.items.size, 2)
    }

    /*@Test
    fun assertHasItem(){
        var firstItem: Item = Item(full_name = "android/architecture-samples", owner = Owner(login = "ownerName"), stargazers_count = 10000, forks_count = 20000, license = License(key = "licenseKey") )
        var secondItem: Item = Item(full_name = "android/architecture-samples", owner = Owner(login = "ownerName"), stargazers_count = 10000, forks_count = 20000, license = License(key = "licenseKey") )

        var listOfItems = arrayListOf<Item>(firstItem, secondItem)

        val repository = GitHubRespository()
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val cacheList = emptyList<Item>()
        Mockito.`when`(mainViewModel.getDownloadedItems())
            .thenReturn(listOfItems)

        mainViewModel.checkIfItensExistsAtList(firstItem)

    }*/
}
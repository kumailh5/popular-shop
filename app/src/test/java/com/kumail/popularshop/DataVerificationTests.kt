package com.kumail.popularshop

import com.kumail.popularshop.PopularShopApplication.Companion.deviceWidth
import com.kumail.popularshop.data.model.FormattedPicture
import com.kumail.popularshop.data.model.Picture
import com.kumail.popularshop.data.model.SaleItem
import com.kumail.popularshop.data.model.getPrice
import com.kumail.popularshop.util.getPictureUrl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

/**
 * Created by kumailhussain on 18/10/2021.
 */
class DataVerificationTests {

    @Mock
    lateinit var item: SaleItem

    @Mock
    lateinit var picture: Picture

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val p1 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P1.jpg",
            640,
            640
        )
        val p2 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P2.jpg",
            150,
            150
        )
        val p4 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P4.jpg",
            210,
            210
        )
        val p5 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P5.jpg",
            320,
            320
        )
        val p6 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P6.jpg",
            480,
            480
        )
        val p7 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P7.jpg",
            960,
            960
        )
        val p8 = FormattedPicture(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P8.jpg",
            1280,
            1280
        )

        picture = Picture(
            1,
            mapOf(
                "p1" to p1,
                "p2" to p2,
                "p4" to p4,
                "p5" to p5,
                "p6" to p6,
                "p7" to p7,
                "p8" to p8
            )
        )
    }

    private fun initSaleItem(price: BigDecimal, pic: Picture) {
        item = SaleItem(
            263090589,
            270,
            25794403,
            "active",
            "S",
            "Exeter, United Kingdom",
            listOf(79),
            "GB",
            "vintage nike puffer jacket\nInternational Shipping - Please ask us for a price",
            15.00,
            4.50,
            listOf(pic),
            price,
            "GBP",
            "2021-10-18T13:09:28.186837Z",
            "2021-09-20T17:39:06.136212Z",
            true,
            "faestygyal-its-definitely-getting-colder-so",
            36,
            mapOf("2" to 1),
            false,
            mapOf("id" to 25794403)
        )
    }

    @Test
    fun `Verify price conversion`() {
        initSaleItem(BigDecimal(69.999), picture)
        assertEquals("£70.00", item.getPrice())
        assertNotEquals("£69.99", item.getPrice())
        assertNotEquals("£69", item.getPrice())

        initSaleItem(BigDecimal(69.99), picture)
        assertEquals("£69.99", item.getPrice())
        assertNotEquals("£70.00", item.getPrice())
        assertNotEquals("£69", item.getPrice())
    }

    @Test
    fun `Verify formatted picture`() {
        initSaleItem(BigDecimal(70), picture)
        deviceWidth = 100
        assertEquals(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P2.jpg",
            item.pictures[0].getPictureUrl()
        )
        deviceWidth = 640
        assertEquals(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P1.jpg",
            item.pictures[0].getPictureUrl()
        )
        deviceWidth = 480
        assertEquals(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P6.jpg",
            item.pictures[0].getPictureUrl()
        )
        deviceWidth = 1000
        assertEquals(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P8.jpg",
            item.pictures[0].getPictureUrl()
        )
        deviceWidth = 1400
        assertEquals(
            "https://media-photos.depop.com/b0/3450029/1104631587_05027a8e7cba4b27b548803717294b58/P8.jpg",
            item.pictures[0].getPictureUrl()
        )
    }
}
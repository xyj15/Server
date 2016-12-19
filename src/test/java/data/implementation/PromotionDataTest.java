package data.implementation;

import other.PromotionType;
import other.SaleType;
import org.junit.Before;
import org.junit.Test;
import po.PromotionPO;

import static org.junit.Assert.*;

/**
 * Created by apple on 2016/12/15.
 */
public class PromotionDataTest {

	private PromotionData promotionData;
	@Before
	public void setUp() throws Exception {
		promotionData = new PromotionData();
	}

	@Test
	public void getPromotion() throws Exception {
		assertEquals("00000", promotionData.getPromotion("00000").getPromotionID());
	}

	@Test
	public void addPromotion() throws Exception {
		PromotionPO model = new PromotionPO("00005","与Vision Stadio的合作折扣", PromotionType.Discount);
		model.setSaleType(SaleType.Enterprise);
		model.setDiscount(0.75);
		model.setEnterprise("Intellij IDEA");
		 assertEquals(true, promotionData.addPromotion(model));
		assertEquals(false, promotionData.addPromotion(model));
	}

	@Test
	public void deletePromotion() throws Exception {
		assertEquals(true, promotionData.deletePromotion("00002"));
		assertEquals(false, promotionData.deletePromotion("00002"));
	}

	@Test
	public void updatePromotion() throws Exception {
		PromotionPO model = new PromotionPO("00005","与VS的友好合作折扣", PromotionType.Discount);
		model.setSaleType(SaleType.Enterprise);
		model.setDiscount(0.75);
		model.setEnterprise("Intellij IDEA");
		promotionData.updatePromotion(model);
	}

	@Test
	public void getPromotionList() throws Exception {
		assertEquals(6, promotionData.getPromotionList().size());
	}

	@Test
	public void getAvailablePromotionID() throws Exception {
		assertEquals("00005", promotionData.getAvailablePromotionID());
	}

}
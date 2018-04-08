package com.ycit.service.builder;

import com.ycit.bean.modal.Goods;
import com.ycit.bean.modal.Store;
import com.ycit.bean.modal.dict.Info;
import com.ycit.bean.vo.GoodsVo;
import com.ycit.service.DictInfoService;
import com.ycit.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * GoodsVo 构建器
 *
 * @author xlch
 * @Date 2018-03-25 14:06
 */
@Service
public class GoodsVoBuilder {

    private DictInfoService dictInfoService;

    private StoreService storeService;

    @Resource
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Resource
    public void setDictInfoService(DictInfoService dictInfoService) {
        this.dictInfoService = dictInfoService;
    }

    public GoodsVo buildGoodsVo(Goods goods) {
        GoodsVo goodsVo = GoodsVo.fromBean(goods);
        Info brandInfo = goods.getBrandId() == null? null: dictInfoService.findById(goods.getBrandId());
        Store store = goods.getStoreId() == null ? null: storeService.findById(goods.getStoreId());
        Info categoryInfo = goods.getCategoryId() == null ? null :dictInfoService.findById(goods.getCategoryId());
        Info purposeInfo = goods.getPurposeId() == null ? null : dictInfoService.findById(goods.getPurposeId());
        goodsVo.setBrand(brandInfo);
        goodsVo.setStore(store);
        goodsVo.setCategory(categoryInfo);
        goodsVo.setPurpose(purposeInfo);
        return goodsVo;
    }

    public List<GoodsVo> buildList(List<Goods> goodses) {
        List<GoodsVo> goodsVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodses)) {
            goodses.forEach(goods -> {
                GoodsVo goodsVo = buildGoodsVo(goods);
                goodsVos.add(goodsVo);
            });
        }
        return goodsVos;
    }

}

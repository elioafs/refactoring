package com.celfocus.training.domain.iteminfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.celfocus.training.domain.AbstractDao;
import com.celfocus.training.util.Utils;

public class ItemInfoDao implements AbstractDao<ItemInfo> {

	List<ItemInfo> items = new ArrayList<>();

	@Override
	public List<ItemInfo> getAll() {
		return items;
	}

	public ItemInfo upsert(ItemInfo itemInfo) {
		Objects.requireNonNull(itemInfo);
		return existsItemInfo(itemInfo.getName()) ? update(itemInfo) : create(itemInfo);
	}

	@Override
	public ItemInfo create(ItemInfo itemInfo) {
		Objects.requireNonNull(itemInfo);
		items.add(itemInfo);
		return itemInfo;
	}

	public ItemInfo update(ItemInfo itemInfo) {
		Objects.requireNonNull(itemInfo);
		ItemInfo savedItemInfo = findByName(itemInfo.getName());
		if (savedItemInfo != null) {
			savedItemInfo.setValue(itemInfo.getValue());
		}
		return savedItemInfo;
	}

	public boolean existsItemInfo(String name) {
		return findByName(name) != null;
	}

	public ItemInfo findByName(String name) {
		if (Utils.isNullOrEmpty(name))
			return null;
		for (ItemInfo itemInfo : items) {
			if (name.equals(itemInfo.getName())) {
				return itemInfo;
			}
		}
		return null;
	}

	@Override
	public void delete(String name) {
		if (existsItemInfo(name)) {
			items.remove(findByName(name));
		}
	}

}

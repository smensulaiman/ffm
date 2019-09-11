package com.diufinalproject.sugarsense.adapters;

public interface RemainderDetailsAdapterListener {
    public void didPressSubmit(String suggestionId, String amount);
    public void onItemClick(int position, String id);
}

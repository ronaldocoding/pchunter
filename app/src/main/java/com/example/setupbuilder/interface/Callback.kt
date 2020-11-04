package com.example.setupbuilder.`interface`

import com.example.setupbuilder.model.Part

interface Callback {
    public fun onRequestSuccess(produtos: ArrayList<Part>);
    public fun onRequestError();

}


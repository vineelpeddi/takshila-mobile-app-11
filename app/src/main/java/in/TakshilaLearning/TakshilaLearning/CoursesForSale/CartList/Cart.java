package in.TakshilaLearning.TakshilaLearning.CoursesForSale.CartList;


import android.util.Log;

import java.util.ArrayList;

import in.TakshilaLearning.TakshilaLearning.CoursesForSale.RvCourseForSaleContentPojo;

import static com.android.volley.VolleyLog.TAG;


public  class Cart  {

    public static ArrayList<RvCourseForSaleContentPojo> cartProducts = new ArrayList<RvCourseForSaleContentPojo>();

    public static ArrayList<RvCourseForSaleContentPojo> getCartProducts() {
        return cartProducts;
    }

    public static String addingToCartList (RvCourseForSaleContentPojo myListData){
        String result = null;
        int e=0;
        for(int i =0; i<cartProducts.size();i++){
            if(cartProducts.get(i).getId().equals(myListData.getId())){
                e=1;
                result = myListData.getName() + " is already added to Cart";
            }
        }
        if(e==0) {
            Log.e(TAG, "added" + myListData.getName());
            cartProducts.add(myListData);
            result = myListData.getName() + " has been successfully added to Cart" ;
        }
        return result;
   }
    public static int getCartSize() {
        return cartProducts.size();
    }

    public boolean checkProductInCart(RvCourseForSaleContentPojo Product) {
        return cartProducts.contains(Product);
    }
    public  static void deleteFromCart(RvCourseForSaleContentPojo Products ){
        for(int i=cartProducts.size()-1; i>=0; i--) {
            if(cartProducts.get(i).getId() == Products.getId()) {
                cartProducts.remove(i);
            }
        }
    }


    public static int total( ) {
        int sum = 0;
        for (int i = 0; i < cartProducts.size(); i++) {
            sum += Integer.parseInt(cartProducts.get(i).getPrice());
        }
        return sum;
    }

}

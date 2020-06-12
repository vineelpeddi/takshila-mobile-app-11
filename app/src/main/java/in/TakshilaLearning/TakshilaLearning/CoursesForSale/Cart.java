package in.TakshilaLearning.TakshilaLearning.CoursesForSale;


import java.util.ArrayList;



public  class Cart  {

    public static ArrayList<RvCourseForSaleContentPojo> cartProducts = new ArrayList<RvCourseForSaleContentPojo>();



   public static void addingToCartList (RvCourseForSaleContentPojo myListData){

       cartProducts.add(myListData);


    }
    public int getCartSize() {

        return cartProducts.size();

    }

    public boolean checkProductInCart(RvCourseForSaleContentPojo aProduct) {

        return cartProducts.contains(aProduct);

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

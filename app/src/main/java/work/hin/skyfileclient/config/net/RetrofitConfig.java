package work.hin.skyfileclient.config.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static work.hin.skyfileclient.config.constant.NET_STATIC.BASE_URL;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class RetrofitConfig {
    private RetrofitConfig() {}

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

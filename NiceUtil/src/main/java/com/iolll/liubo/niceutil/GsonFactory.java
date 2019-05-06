package com.iolll.liubo.niceutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.iolll.liubo.ifunction.IFunction;

import java.io.IOException;

/**
 * Created by LiuBo on 2019/1/4.
 */
public class GsonFactory {
    /**
     * 使用默认的基本类型 null 转 默认值
     * @return
     */
    public static Gson create() {
        return new GsonBuilder()
//                .registerTypeAdapter(ActivityBean.class,new ActivityBeanAdapter())
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create();
    }

    /**
     * 可通过这个函数进行自定义规则
     * @param run
     * 如果没有IFunction 请依赖 IolllFunction Lib
     * @return
     */
    public static Gson create(IFunction.Apply<GsonBuilder> run) {
        if (null==run)
            throw new NullPointerException("GsonFactory method param IFunction.Run must not be null ");
        return run.apply(new GsonBuilder())
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create();
    }

    private static class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType == String.class) {
                return (TypeAdapter<T>) new StringNullAdapter();
            } else if (rawType == Integer.class) {
                return (TypeAdapter<T>) new IntegerNullAdapter();
            }else if (rawType ==Boolean.class){
                return (TypeAdapter<T>) new BooleanNullAdapter();
            }
            return null;
        }

        private class StringNullAdapter extends TypeAdapter<String> {
            @Override
            public String read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";
                }
                return reader.nextString();
            }

            @Override
            public void write(JsonWriter writer, String value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            }
        }

        private class IntegerNullAdapter extends TypeAdapter<Integer> {
            @Override
            public Integer read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0;
                }
                return reader.nextInt();
            }

            @Override
            public void write(JsonWriter writer, Integer value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);

            }
        }

        private class BooleanNullAdapter extends TypeAdapter<Boolean> {
            @Override
            public void write(JsonWriter out, Boolean value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value);
            }

            @Override
            public Boolean read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return false;
                }
                return in.nextBoolean();
            }
        }
    }
//    private static class ActivityBeanAdapter implements JsonDeserializer<ActivityBean> {
//
//        @Override
//        public ActivityBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            final JsonObject jsonObject = json.getAsJsonObject();
//            final JsonElement jsonTitle = jsonObject.get("title");
//            final String title = jsonTitle.getAsString();
//            final String isbn10 = jsonObject.get("isbn-10").getAsString();
//            final String isbn13 = jsonObject.get("isbn-13").getAsString();
//            final JsonArray jsonAuthorsArray = jsonObject.get("authors").getAsJsonArray();
//            final String[] authors = new String[jsonAuthorsArray.size()];
//            for (int i = 0; i < authors.length; i++) {
//                final JsonElement jsonAuthor = jsonAuthorsArray.get(i);
//                authors[i] = jsonAuthor.getAsString();
//            }
//            final ActivityBean activityBean = new ActivityBean();
//            activityBean.setName(title);
//            return activityBean;
//        }
//    }
}

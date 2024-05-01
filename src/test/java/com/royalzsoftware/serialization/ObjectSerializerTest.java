package com.royalzsoftware.serialization;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.royalzsoftware.uno.UnoPlayer;
import com.royalzsoftware.uno.cards.CardStack;

public class ObjectSerializerTest {
    
    class JsonSerializer {

        public String serialize(Serializable serializable) {
            Serializer serializer = serializable.getSerializer();

            if (serializer instanceof StringSerializer) {
                return '"' + (String) serializer.serialize() + '"';
            }
            
            if (serializer instanceof NumberSerializer) {
                return Integer.toString((Integer) serializer.serialize());
            }

            if (serializer instanceof ArraySerializer) {
                List<Serializable> serializers = (List<Serializable>) serializer.serialize();
                return '[' + String.join(",", serializers.stream().map(s -> this.serialize(s)).toList()) + ']';
            }
            if (serializer instanceof ObjectSerializer) {
                System.out.println(serializer);
                Map<String, Serializable> kvMap = (Map<String, Serializable>) serializer.serialize();

                kvMap.put("type", new StringSerializable(serializable.getClass().getName()));

                StringBuffer buff = new StringBuffer();

                int size = kvMap.size();

                int index = 0;
                for (Entry<String, Serializable> entry : kvMap.entrySet()) {
                    Serializable s = entry.getValue();
                    String value = this.serialize(s);

                    buff.append(String.format("\"%s\": %s", entry.getKey(), value));
                    if (index != size - 1)
                        buff.append(",");
                    index++;
                    buff.append('\n');
                }

                return "{" + buff.toString() + "}";
            }
            return "undefined";
        }
    }

    @Test
    public void testSerialize() {
        var p = new UnoPlayer("Alex");

        p.addCard(CardStack.getInstance().drawCard());
        p.addCard(CardStack.getInstance().drawCard());
        p.addCard(CardStack.getInstance().drawCard());

        System.out.println(
            new JsonSerializer().serialize(p)
        );
    }
}

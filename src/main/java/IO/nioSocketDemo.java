package IO;


import javax.imageio.IIOException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

//nio
public class nioSocketDemo {

    private Selector selector;//管理器

    public void initServer(int port) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    public void listenSelector(SelectionKey selectionKey) throws IOException {
        //轮训监听 selector
        while (true){
            //等待客户连接
            //select 模型 多路复用
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                //处理请求
                handler(key);
            }
        }
    }

    public void handler(SelectionKey key){
        if (key.isAcceptable()){
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        }
    }

}

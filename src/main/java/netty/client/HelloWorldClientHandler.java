package netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2019-03-05 14:08
 **/
public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {

    private byte[] req;
    private int counter;
    public HelloWorldClientHandler() {
        /*req = ("Unless required by applicable law or agreed to in writing, software\n" +
                "  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "  See the License for the specific language governing permissions and\n" +
                "  limitations under the License.This connector uses the BIO implementation that requires the JSSE\n" +
                "  style configuration. When using the APR/native implementation, the\n" +
                "  penSSL style configuration is required as described in the APR/native\n" +
                "  documentation.An Engine represents the entry point (within Catalina) that processes\n" +
                "  every request.  The Engine implementation for Tomcat stand alone\n" +
                "  analyzes the HTTP headers included with the request, and passes them\n" +
                "  on to the appropriate Host (virtual host)# Unless required by applicable law or agreed to in writing, software\n" +
                "# distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "# See the License for the specific language governing permissions and\n" +
                "# limitations under the License.# For example, set the org.apache.catalina.util.LifecycleBase logger to log\n" +
                "# each component that extends LifecycleBase changing state:\n" +
                "#org.apache.catalina.util.LifecycleBase.level = FINE"
        ).getBytes();*/

        req = ("Unless required by applicable law or agreed to in writing, software\t" +
                "  distributed under the License is distributed on an \"AS IS\" BASIS,\t" +
                "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\t" +
                "  See the License for the specific language governing permissions and\t" +
                "  limitations under the License.This connector uses the BIO implementation that requires the JSSE\t" +
                "  style configuration. When using the APR/native implementation, the\t" +
                "  penSSL style configuration is required as described in the APR/native\t" +
                "  documentation.An Engine represents the entry point (within Catalina) that processes\t" +
                "  every request.  The Engine implementation for Tomcat stand alone\t" +
                "  analyzes the HTTP headers included with the request, and passes them\t" +
                "  on to the appropriate Host (virtual host)# Unless required by applicable law or agreed to in writing, software\t" +
                "# distributed under the License is distributed on an \"AS IS\" BASIS,\t" +
                "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\t" +
                "# See the License for the specific language governing permissions and\t" +
                "# limitations under the License.# For example, set the org.apache.catalina.util.LifecycleBase logger to log\t" +
                "# each component that extends LifecycleBase changing state:\t" +
                "#org.apache.catalina.util.LifecycleBase.level = FINE\t"
        ).getBytes();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message;
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String buf = (String)msg;
        System.out.println("Now is : " + buf + " ; the counter is : "+ (++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}

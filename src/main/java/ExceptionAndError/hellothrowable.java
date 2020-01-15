package ExceptionAndError;

public class hellothrowable {
    /*
    * ┌───────────┐
                     │  Object   │
                     └───────────┘
                           ▲
                           │
                     ┌───────────┐
                     │ Throwable │
                     └───────────┘
                           ▲
                 ┌─────────┴─────────┐
                 │                   │
           ┌───────────┐       ┌───────────┐
           │   Error   │       │ Exception │                                 error 严重的错误 OutOfMemoryError：内存耗尽
           └───────────┘       └───────────┘                                        NoClassDefFoundError：无法加载某个Class
                 ▲                   ▲                                              StackOverflowError：栈溢出  等
         ┌───────┘              ┌────┴──────────┐                            exception 运行时错误 应该捕获处理
         │                      │               │                                    Exception又分为两大类：RuntimeException以及它的子类；
┌─────────────────┐    ┌─────────────────┐┌───────────┐                             非RuntimeException（包括IOException、ReflectiveOperationException等等）
│OutOfMemoryError │... │RuntimeException ││IOException│...
└─────────────────┘    └─────────────────┘└───────────┘
                                ▲
                    ┌───────────┴─────────────┐
                    │                         │
         ┌─────────────────────┐ ┌─────────────────────────┐
         │NullPointerException │ │IllegalArgumentException │...
         └─────────────────────┘ └─────────────────────────┘
    *
    *
    *  */


}

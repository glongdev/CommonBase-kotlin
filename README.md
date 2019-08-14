# CommonBase-kotlin
基于kotlin的项目架构：将业务模块拆分开

> 架构模式:MVVM

![](/screenshot/架构.png)

其中，各个模块的职能：

Common：主要封装公共方法，扩展方法，线程切换等；

SDK/Lib：如经过修改/封装的第三方框架（Glide、Gson、IjkPlayer等等）、自定义的体积比较大的公共模块（lib_user、lib_xxxView）；

base_component:跟业务逻辑紧密相关的模块，封装自定义View、业务中的资源文件（shape、selector、strings）等等；

业务模块：具体的业务模块，如：module_search（搜索模块）、module_user（用户模块）等等；

App是壳工程，理论上无业务逻辑；


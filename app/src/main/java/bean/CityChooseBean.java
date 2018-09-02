package bean;

import java.util.List;

/**
 * Created by mayn on 2018/7/23.
 */

public class CityChooseBean {

    /**
     * code : 1000
     * result : {"city":[{"id":110000,"pid":0,"initial":"B","name":"北京","son":[{"id":110100,"pid":110000,"initial":"B","name":"北京市"}]},{"id":120000,"pid":0,"initial":"T","name":"天津","son":[{"id":120100,"pid":120000,"initial":"T","name":"天津市"}]},{"id":130000,"pid":0,"initial":"H","name":"河北","son":[{"id":130100,"pid":130000,"initial":"S","name":"石家庄"},{"id":130200,"pid":130000,"initial":"T","name":"唐山"},{"id":130300,"pid":130000,"initial":"Q","name":"秦皇岛"},{"id":130400,"pid":130000,"initial":"H","name":"邯郸"},{"id":130500,"pid":130000,"initial":"X","name":"邢台"},{"id":130600,"pid":130000,"initial":"B","name":"保定"},{"id":130700,"pid":130000,"initial":"Z","name":"张家口"},{"id":130800,"pid":130000,"initial":"C","name":"承德"},{"id":130900,"pid":130000,"initial":"C","name":"沧州"},{"id":131000,"pid":130000,"initial":"L","name":"廊坊"},{"id":131100,"pid":130000,"initial":"H","name":"衡水"}]},{"id":140000,"pid":0,"initial":"S","name":"山西","son":[{"id":140100,"pid":140000,"initial":"T","name":"太原"},{"id":140200,"pid":140000,"initial":"D","name":"大同"},{"id":140300,"pid":140000,"initial":"Y","name":"阳泉"},{"id":140400,"pid":140000,"initial":"C","name":"长治"},{"id":140500,"pid":140000,"initial":"J","name":"晋城"},{"id":140600,"pid":140000,"initial":"S","name":"朔州"},{"id":140700,"pid":140000,"initial":"J","name":"晋中"},{"id":140800,"pid":140000,"initial":"Y","name":"运城"},{"id":140900,"pid":140000,"initial":"X","name":"忻州"},{"id":141000,"pid":140000,"initial":"L","name":"临汾"},{"id":141100,"pid":140000,"initial":"L","name":"吕梁"}]},{"id":150000,"pid":0,"initial":"N","name":"内蒙","son":[{"id":150100,"pid":150000,"initial":"H","name":"呼和浩特"},{"id":150200,"pid":150000,"initial":"B","name":"包头"},{"id":150300,"pid":150000,"initial":"W","name":"乌海"},{"id":150400,"pid":150000,"initial":"C","name":"赤峰"},{"id":150500,"pid":150000,"initial":"T","name":"通辽"},{"id":150600,"pid":150000,"initial":"E","name":"鄂尔多斯"},{"id":150700,"pid":150000,"initial":"H","name":"呼伦贝尔"},{"id":150800,"pid":150000,"initial":"B","name":"巴彦淖尔"},{"id":150900,"pid":150000,"initial":"W","name":"乌兰察布"},{"id":152200,"pid":150000,"initial":"X","name":"兴安盟"},{"id":152500,"pid":150000,"initial":"X","name":"锡林郭勒盟"},{"id":152900,"pid":150000,"initial":"A","name":"阿拉善盟"}]},{"id":210000,"pid":0,"initial":"L","name":"辽宁","son":[{"id":210100,"pid":210000,"initial":"S","name":"沈阳"},{"id":210200,"pid":210000,"initial":"D","name":"大连"},{"id":210300,"pid":210000,"initial":"A","name":"鞍山"},{"id":210400,"pid":210000,"initial":"F","name":"抚顺"},{"id":210500,"pid":210000,"initial":"B","name":"本溪"},{"id":210600,"pid":210000,"initial":"D","name":"丹东"},{"id":210700,"pid":210000,"initial":"J","name":"锦州"},{"id":210800,"pid":210000,"initial":"Y","name":"营口"},{"id":210900,"pid":210000,"initial":"F","name":"阜新"},{"id":211000,"pid":210000,"initial":"L","name":"辽阳"},{"id":211100,"pid":210000,"initial":"P","name":"盘锦"},{"id":211200,"pid":210000,"initial":"T","name":"铁岭"},{"id":211300,"pid":210000,"initial":"Z","name":"朝阳"},{"id":211400,"pid":210000,"initial":"H","name":"葫芦岛"}]},{"id":220000,"pid":0,"initial":"J","name":"吉林","son":[{"id":220100,"pid":220000,"initial":"C","name":"长春"},{"id":220200,"pid":220000,"initial":"J","name":"吉林"},{"id":220300,"pid":220000,"initial":"S","name":"四平"},{"id":220400,"pid":220000,"initial":"L","name":"辽源"},{"id":220500,"pid":220000,"initial":"T","name":"通化"},{"id":220600,"pid":220000,"initial":"B","name":"白山"},{"id":220700,"pid":220000,"initial":"S","name":"松原"},{"id":220800,"pid":220000,"initial":"B","name":"白城"},{"id":222400,"pid":220000,"initial":"Y","name":"延边"}]},{"id":230000,"pid":0,"initial":"H","name":"黑龙江","son":[{"id":230100,"pid":230000,"initial":"H","name":"哈尔滨"},{"id":230200,"pid":230000,"initial":"Q","name":"齐齐哈尔"},{"id":230300,"pid":230000,"initial":"J","name":"鸡西"},{"id":230400,"pid":230000,"initial":"H","name":"鹤岗"},{"id":230500,"pid":230000,"initial":"S","name":"双鸭山"},{"id":230600,"pid":230000,"initial":"D","name":"大庆"},{"id":230700,"pid":230000,"initial":"Y","name":"伊春"},{"id":230800,"pid":230000,"initial":"J","name":"佳木斯"},{"id":230900,"pid":230000,"initial":"Q","name":"七台河"},{"id":231000,"pid":230000,"initial":"M","name":"牡丹江"},{"id":231100,"pid":230000,"initial":"H","name":"黑河"},{"id":231200,"pid":230000,"initial":"S","name":"绥化"},{"id":232700,"pid":230000,"initial":"D","name":"大兴安岭"}]},{"id":310000,"pid":0,"initial":"S","name":"上海","son":[{"id":310100,"pid":310000,"initial":"S","name":"上海市"}]},{"id":320000,"pid":0,"initial":"J","name":"江苏","son":[{"id":320100,"pid":320000,"initial":"N","name":"南京"},{"id":320200,"pid":320000,"initial":"W","name":"无锡"},{"id":320300,"pid":320000,"initial":"X","name":"徐州"},{"id":320400,"pid":320000,"initial":"C","name":"常州"},{"id":320500,"pid":320000,"initial":"S","name":"苏州"},{"id":320600,"pid":320000,"initial":"N","name":"南通"},{"id":320700,"pid":320000,"initial":"L","name":"连云港"},{"id":320800,"pid":320000,"initial":"H","name":"淮安"},{"id":320900,"pid":320000,"initial":"Y","name":"盐城"},{"id":321000,"pid":320000,"initial":"Y","name":"扬州"},{"id":321100,"pid":320000,"initial":"Z","name":"镇江"},{"id":321200,"pid":320000,"initial":"T","name":"泰州"},{"id":321300,"pid":320000,"initial":"S","name":"宿迁"}]},{"id":330000,"pid":0,"initial":"Z","name":"浙江","son":[{"id":330100,"pid":330000,"initial":"H","name":"杭州"},{"id":330200,"pid":330000,"initial":"N","name":"宁波"},{"id":330300,"pid":330000,"initial":"W","name":"温州"},{"id":330400,"pid":330000,"initial":"J","name":"嘉兴"},{"id":330500,"pid":330000,"initial":"H","name":"湖州"},{"id":330600,"pid":330000,"initial":"S","name":"绍兴"},{"id":330700,"pid":330000,"initial":"J","name":"金华"},{"id":330800,"pid":330000,"initial":"Q","name":"衢州"},{"id":330900,"pid":330000,"initial":"Z","name":"舟山"},{"id":331000,"pid":330000,"initial":"T","name":"台州"},{"id":331100,"pid":330000,"initial":"L","name":"丽水"}]},{"id":340000,"pid":0,"initial":"A","name":"安徽","son":[{"id":340100,"pid":340000,"initial":"H","name":"合肥"},{"id":340200,"pid":340000,"initial":"W","name":"芜湖"},{"id":340300,"pid":340000,"initial":"B","name":"蚌埠"},{"id":340400,"pid":340000,"initial":"H","name":"淮南"},{"id":340500,"pid":340000,"initial":"M","name":"马鞍山"},{"id":340600,"pid":340000,"initial":"H","name":"淮北"},{"id":340700,"pid":340000,"initial":"T","name":"铜陵"},{"id":340800,"pid":340000,"initial":"A","name":"安庆"},{"id":341000,"pid":340000,"initial":"H","name":"黄山"},{"id":341100,"pid":340000,"initial":"C","name":"滁州"},{"id":341200,"pid":340000,"initial":"F","name":"阜阳"},{"id":341300,"pid":340000,"initial":"S","name":"宿州"},{"id":341400,"pid":340000,"initial":"C","name":"巢湖"},{"id":341500,"pid":340000,"initial":"L","name":"六安"},{"id":341600,"pid":340000,"initial":"B","name":"亳州"},{"id":341700,"pid":340000,"initial":"C","name":"池州"},{"id":341800,"pid":340000,"initial":"X","name":"宣城"}]},{"id":350000,"pid":0,"initial":"F","name":"福建","son":[{"id":350100,"pid":350000,"initial":"F","name":"福州"},{"id":350200,"pid":350000,"initial":"X","name":"厦门"},{"id":350300,"pid":350000,"initial":"P","name":"莆田"},{"id":350400,"pid":350000,"initial":"S","name":"三明"},{"id":350500,"pid":350000,"initial":"Q","name":"泉州"},{"id":350600,"pid":350000,"initial":"Z","name":"漳州"},{"id":350700,"pid":350000,"initial":"N","name":"南平"},{"id":350800,"pid":350000,"initial":"L","name":"龙岩"},{"id":350900,"pid":350000,"initial":"N","name":"宁德"}]},{"id":360000,"pid":0,"initial":"J","name":"江西","son":[{"id":360100,"pid":360000,"initial":"N","name":"南昌"},{"id":360200,"pid":360000,"initial":"J","name":"景德镇"},{"id":360300,"pid":360000,"initial":"P","name":"萍乡"},{"id":360400,"pid":360000,"initial":"J","name":"九江"},{"id":360500,"pid":360000,"initial":"X","name":"新余"},{"id":360600,"pid":360000,"initial":"Y","name":"鹰潭"},{"id":360700,"pid":360000,"initial":"G","name":"赣州"},{"id":360800,"pid":360000,"initial":"J","name":"吉安"},{"id":360900,"pid":360000,"initial":"Y","name":"宜春"},{"id":361000,"pid":360000,"initial":"F","name":"抚州"},{"id":361100,"pid":360000,"initial":"S","name":"上饶"}]},{"id":370000,"pid":0,"initial":"S","name":"山东","son":[{"id":370100,"pid":370000,"initial":"J","name":"济南"},{"id":370200,"pid":370000,"initial":"Q","name":"青岛"},{"id":370300,"pid":370000,"initial":"Z","name":"淄博"},{"id":370400,"pid":370000,"initial":"Z","name":"枣庄"},{"id":370500,"pid":370000,"initial":"D","name":"东营"},{"id":370600,"pid":370000,"initial":"Y","name":"烟台"},{"id":370700,"pid":370000,"initial":"W","name":"潍坊"},{"id":370800,"pid":370000,"initial":"J","name":"济宁"},{"id":370900,"pid":370000,"initial":"T","name":"泰安"},{"id":371000,"pid":370000,"initial":"W","name":"威海"},{"id":371100,"pid":370000,"initial":"R","name":"日照"},{"id":371200,"pid":370000,"initial":"L","name":"莱芜"},{"id":371300,"pid":370000,"initial":"L","name":"临沂"},{"id":371400,"pid":370000,"initial":"D","name":"德州"},{"id":371500,"pid":370000,"initial":"L","name":"聊城"},{"id":371600,"pid":370000,"initial":"B","name":"滨州"},{"id":371700,"pid":370000,"initial":"H","name":"菏泽"}]},{"id":410000,"pid":0,"initial":"H","name":"河南","son":[{"id":410100,"pid":410000,"initial":"Z","name":"郑州"},{"id":410200,"pid":410000,"initial":"K","name":"开封"},{"id":410300,"pid":410000,"initial":"L","name":"洛阳"},{"id":410400,"pid":410000,"initial":"P","name":"平顶山"},{"id":410500,"pid":410000,"initial":"A","name":"安阳"},{"id":410600,"pid":410000,"initial":"H","name":"鹤壁"},{"id":410700,"pid":410000,"initial":"X","name":"新乡"},{"id":410800,"pid":410000,"initial":"J","name":"焦作"},{"id":410881,"pid":410000,"initial":"J","name":"济源"},{"id":410900,"pid":410000,"initial":"P","name":"濮阳"},{"id":411000,"pid":410000,"initial":"X","name":"许昌"},{"id":411100,"pid":410000,"initial":"T","name":"漯河"},{"id":411200,"pid":410000,"initial":"S","name":"三门峡"},{"id":411300,"pid":410000,"initial":"N","name":"南阳"},{"id":411400,"pid":410000,"initial":"S","name":"商丘"},{"id":411500,"pid":410000,"initial":"X","name":"信阳"},{"id":411600,"pid":410000,"initial":"Z","name":"周口"},{"id":411700,"pid":410000,"initial":"Z","name":"驻马店"}]},{"id":420000,"pid":0,"initial":"H","name":"湖北","son":[{"id":420100,"pid":420000,"initial":"W","name":"武汉"},{"id":420200,"pid":420000,"initial":"H","name":"黄石"},{"id":420300,"pid":420000,"initial":"S","name":"十堰"},{"id":420500,"pid":420000,"initial":"Y","name":"宜昌"},{"id":420600,"pid":420000,"initial":"X","name":"襄樊"},{"id":420700,"pid":420000,"initial":"E","name":"鄂州"},{"id":420800,"pid":420000,"initial":"J","name":"荆门"},{"id":420900,"pid":420000,"initial":"X","name":"孝感"},{"id":421000,"pid":420000,"initial":"J","name":"荆州"},{"id":421100,"pid":420000,"initial":"H","name":"黄冈"},{"id":421200,"pid":420000,"initial":"X","name":"咸宁"},{"id":421300,"pid":420000,"initial":"S","name":"随州"},{"id":422800,"pid":420000,"initial":"E","name":"恩施"},{"id":429004,"pid":420000,"initial":"X","name":"仙桃"},{"id":429005,"pid":420000,"initial":"Q","name":"潜江"},{"id":429006,"pid":420000,"initial":"T","name":"天门"},{"id":429021,"pid":420000,"initial":"S","name":"神农架林区"}]},{"id":430000,"pid":0,"initial":"H","name":"湖南","son":[{"id":430100,"pid":430000,"initial":"C","name":"长沙"},{"id":430200,"pid":430000,"initial":"Z","name":"株洲"},{"id":430300,"pid":430000,"initial":"X","name":"湘潭"},{"id":430400,"pid":430000,"initial":"H","name":"衡阳"},{"id":430500,"pid":430000,"initial":"S","name":"邵阳"},{"id":430600,"pid":430000,"initial":"Y","name":"岳阳"},{"id":430700,"pid":430000,"initial":"C","name":"常德"},{"id":430800,"pid":430000,"initial":"Z","name":"张家界"},{"id":430900,"pid":430000,"initial":"Y","name":"益阳"},{"id":431000,"pid":430000,"initial":"C","name":"郴州"},{"id":431100,"pid":430000,"initial":"Y","name":"永州"},{"id":431200,"pid":430000,"initial":"H","name":"怀化"},{"id":431300,"pid":430000,"initial":"L","name":"娄底"},{"id":433100,"pid":430000,"initial":"X","name":"湘西"}]},{"id":440000,"pid":0,"initial":"G","name":"广东","son":[{"id":440100,"pid":440000,"initial":"G","name":"广州"},{"id":440200,"pid":440000,"initial":"S","name":"韶关"},{"id":440300,"pid":440000,"initial":"S","name":"深圳"},{"id":440400,"pid":440000,"initial":"Z","name":"珠海"},{"id":440500,"pid":440000,"initial":"S","name":"汕头"},{"id":440600,"pid":440000,"initial":"F","name":"佛山"},{"id":440700,"pid":440000,"initial":"J","name":"江门"},{"id":440800,"pid":440000,"initial":"Z","name":"湛江"},{"id":440900,"pid":440000,"initial":"M","name":"茂名"},{"id":441200,"pid":440000,"initial":"Z","name":"肇庆"},{"id":441300,"pid":440000,"initial":"H","name":"惠州"},{"id":441400,"pid":440000,"initial":"M","name":"梅州"},{"id":441500,"pid":440000,"initial":"S","name":"汕尾"},{"id":441600,"pid":440000,"initial":"H","name":"河源"},{"id":441700,"pid":440000,"initial":"Y","name":"阳江"},{"id":441800,"pid":440000,"initial":"Q","name":"清远"},{"id":441900,"pid":440000,"initial":"D","name":"东莞"},{"id":442000,"pid":440000,"initial":"Z","name":"中山"},{"id":445100,"pid":440000,"initial":"C","name":"潮州"},{"id":445200,"pid":440000,"initial":"J","name":"揭阳"},{"id":445300,"pid":440000,"initial":"Y","name":"云浮"}]},{"id":450000,"pid":0,"initial":"G","name":"广西","son":[{"id":450100,"pid":450000,"initial":"N","name":"南宁"},{"id":450200,"pid":450000,"initial":"L","name":"柳州"},{"id":450300,"pid":450000,"initial":"G","name":"桂林"},{"id":450400,"pid":450000,"initial":"W","name":"梧州"},{"id":450500,"pid":450000,"initial":"B","name":"北海"},{"id":450600,"pid":450000,"initial":"F","name":"防城港"},{"id":450700,"pid":450000,"initial":"Q","name":"钦州"},{"id":450800,"pid":450000,"initial":"G","name":"贵港"},{"id":450900,"pid":450000,"initial":"Y","name":"玉林"},{"id":451000,"pid":450000,"initial":"B","name":"百色"},{"id":451100,"pid":450000,"initial":"H","name":"贺州"},{"id":451200,"pid":450000,"initial":"H","name":"河池"},{"id":451300,"pid":450000,"initial":"L","name":"来宾"},{"id":451400,"pid":450000,"initial":"C","name":"崇左"}]},{"id":460000,"pid":0,"initial":"H","name":"海南","son":[{"id":460100,"pid":460000,"initial":"H","name":"海口"},{"id":460200,"pid":460000,"initial":"S","name":"三亚"},{"id":469001,"pid":460000,"initial":"W","name":"五指山"},{"id":469002,"pid":460000,"initial":"Q","name":"琼海"},{"id":469003,"pid":460000,"initial":"D","name":"儋州"},{"id":469005,"pid":460000,"initial":"W","name":"文昌"},{"id":469006,"pid":460000,"initial":"W","name":"万宁"},{"id":469007,"pid":460000,"initial":"D","name":"东方"},{"id":469025,"pid":460000,"initial":"D","name":"定安"},{"id":469026,"pid":460000,"initial":"T","name":"屯昌"},{"id":469027,"pid":460000,"initial":"C","name":"澄迈"},{"id":469028,"pid":460000,"initial":"L","name":"临高"},{"id":469030,"pid":460000,"initial":"B","name":"白沙"},{"id":469031,"pid":460000,"initial":"C","name":"昌江"},{"id":469033,"pid":460000,"initial":"L","name":"乐东"},{"id":469034,"pid":460000,"initial":"L","name":"陵水"},{"id":469035,"pid":460000,"initial":"B","name":"保亭"},{"id":469036,"pid":460000,"initial":"Q","name":"琼中"},{"id":469037,"pid":460000,"initial":"X","name":"西沙"},{"id":469038,"pid":460000,"initial":"N","name":"南沙"},{"id":469039,"pid":460000,"initial":"Z","name":"中沙"}]},{"id":500000,"pid":0,"initial":"C","name":"重庆"},{"id":510000,"pid":0,"initial":"S","name":"四川","son":[{"id":510100,"pid":510000,"initial":"C","name":"成都"},{"id":510300,"pid":510000,"initial":"Z","name":"自贡"},{"id":510400,"pid":510000,"initial":"P","name":"攀枝花"},{"id":510500,"pid":510000,"initial":"L","name":"泸州"},{"id":510600,"pid":510000,"initial":"D","name":"德阳"},{"id":510700,"pid":510000,"initial":"M","name":"绵阳"},{"id":510800,"pid":510000,"initial":"G","name":"广元"},{"id":510900,"pid":510000,"initial":"S","name":"遂宁"},{"id":511000,"pid":510000,"initial":"N","name":"内江"},{"id":511100,"pid":510000,"initial":"L","name":"乐山"},{"id":511300,"pid":510000,"initial":"N","name":"南充"},{"id":511400,"pid":510000,"initial":"M","name":"眉山"},{"id":511500,"pid":510000,"initial":"Y","name":"宜宾"},{"id":511600,"pid":510000,"initial":"G","name":"广安"},{"id":511700,"pid":510000,"initial":"D","name":"达州"},{"id":511800,"pid":510000,"initial":"Y","name":"雅安"},{"id":511900,"pid":510000,"initial":"B","name":"巴中"},{"id":512000,"pid":510000,"initial":"Z","name":"资阳"},{"id":513200,"pid":510000,"initial":"A","name":"阿坝"},{"id":513300,"pid":510000,"initial":"G","name":"甘孜"},{"id":513400,"pid":510000,"initial":"L","name":"凉山"}]},{"id":520000,"pid":0,"initial":"G","name":"贵州","son":[{"id":520100,"pid":520000,"initial":"G","name":"贵阳"},{"id":520200,"pid":520000,"initial":"L","name":"六盘水"},{"id":520300,"pid":520000,"initial":"Z","name":"遵义"},{"id":520400,"pid":520000,"initial":"A","name":"安顺"},{"id":522200,"pid":520000,"initial":"T","name":"铜仁"},{"id":522300,"pid":520000,"initial":"Q","name":"黔西"},{"id":522400,"pid":520000,"initial":"B","name":"毕节"},{"id":522600,"pid":520000,"initial":"Q","name":"黔东"},{"id":522700,"pid":520000,"initial":"Q","name":"黔南"}]},{"id":530000,"pid":0,"initial":"Y","name":"云南","son":[{"id":530100,"pid":530000,"initial":"K","name":"昆明"},{"id":530300,"pid":530000,"initial":"Q","name":"曲靖"},{"id":530400,"pid":530000,"initial":"Y","name":"玉溪"},{"id":530500,"pid":530000,"initial":"B","name":"保山"},{"id":530600,"pid":530000,"initial":"Z","name":"昭通"},{"id":530700,"pid":530000,"initial":"L","name":"丽江"},{"id":530800,"pid":530000,"initial":"S","name":"思茅"},{"id":530900,"pid":530000,"initial":"L","name":"临沧"},{"id":532300,"pid":530000,"initial":"C","name":"楚雄"},{"id":532500,"pid":530000,"initial":"H","name":"红河"},{"id":532600,"pid":530000,"initial":"W","name":"文山"},{"id":532800,"pid":530000,"initial":"X","name":"西双版纳"},{"id":532900,"pid":530000,"initial":"D","name":"大理"},{"id":533100,"pid":530000,"initial":"D","name":"德宏"},{"id":533300,"pid":530000,"initial":"N","name":"怒江"},{"id":533400,"pid":530000,"initial":"D","name":"迪庆"}]},{"id":540000,"pid":0,"initial":"X","name":"西藏","son":[{"id":540100,"pid":540000,"initial":"L","name":"拉萨"},{"id":542100,"pid":540000,"initial":"C","name":"昌都"},{"id":542200,"pid":540000,"initial":"S","name":"山南"},{"id":542300,"pid":540000,"initial":"R","name":"日喀则"},{"id":542400,"pid":540000,"initial":"N","name":"那曲"},{"id":542500,"pid":540000,"initial":"A","name":"阿里"},{"id":542600,"pid":540000,"initial":"L","name":"林芝"}]},{"id":610000,"pid":0,"initial":"S","name":"陕西","son":[{"id":610100,"pid":610000,"initial":"X","name":"西安"},{"id":610200,"pid":610000,"initial":"T","name":"铜川"},{"id":610300,"pid":610000,"initial":"B","name":"宝鸡"},{"id":610400,"pid":610000,"initial":"X","name":"咸阳"},{"id":610500,"pid":610000,"initial":"W","name":"渭南"},{"id":610600,"pid":610000,"initial":"Y","name":"延安"},{"id":610700,"pid":610000,"initial":"H","name":"汉中"},{"id":610800,"pid":610000,"initial":"Y","name":"榆林"},{"id":610900,"pid":610000,"initial":"A","name":"安康"},{"id":611000,"pid":610000,"initial":"S","name":"商洛"}]},{"id":620000,"pid":0,"initial":"G","name":"甘肃","son":[{"id":620100,"pid":620000,"initial":"L","name":"兰州"},{"id":620200,"pid":620000,"initial":"J","name":"嘉峪关"},{"id":620300,"pid":620000,"initial":"J","name":"金昌"},{"id":620400,"pid":620000,"initial":"B","name":"白银"},{"id":620500,"pid":620000,"initial":"T","name":"天水"},{"id":620600,"pid":620000,"initial":"W","name":"武威"},{"id":620700,"pid":620000,"initial":"Z","name":"张掖"},{"id":620800,"pid":620000,"initial":"P","name":"平凉"},{"id":620900,"pid":620000,"initial":"J","name":"酒泉"},{"id":621000,"pid":620000,"initial":"Q","name":"庆阳"},{"id":621100,"pid":620000,"initial":"D","name":"定西"},{"id":621200,"pid":620000,"initial":"L","name":"陇南"},{"id":622900,"pid":620000,"initial":"L","name":"临夏"},{"id":623000,"pid":620000,"initial":"G","name":"甘南"}]},{"id":630000,"pid":0,"initial":"Q","name":"青海","son":[{"id":630100,"pid":630000,"initial":"X","name":"西宁"},{"id":632100,"pid":630000,"initial":"H","name":"海东"},{"id":632200,"pid":630000,"initial":"H","name":"海北"},{"id":632300,"pid":630000,"initial":"H","name":"黄南"},{"id":632500,"pid":630000,"initial":"H","name":"海南"},{"id":632600,"pid":630000,"initial":"G","name":"果洛"},{"id":632700,"pid":630000,"initial":"Y","name":"玉树"},{"id":632800,"pid":630000,"initial":"H","name":"海西"}]},{"id":640000,"pid":0,"initial":"N","name":"宁夏","son":[{"id":640100,"pid":640000,"initial":"Y","name":"银川"},{"id":640200,"pid":640000,"initial":"S","name":"石嘴山"},{"id":640300,"pid":640000,"initial":"W","name":"吴忠"},{"id":640400,"pid":640000,"initial":"G","name":"固原"},{"id":640500,"pid":640000,"initial":"Z","name":"中卫"}]},{"id":650000,"pid":0,"initial":"X","name":"新疆","son":[{"id":650100,"pid":650000,"initial":"W","name":"乌鲁木齐"},{"id":650200,"pid":650000,"initial":"K","name":"克拉玛依"},{"id":652100,"pid":650000,"initial":"T","name":"吐鲁番"},{"id":652200,"pid":650000,"initial":"H","name":"哈密"},{"id":652300,"pid":650000,"initial":"C","name":"昌吉"},{"id":652700,"pid":650000,"initial":"B","name":"博尔塔拉"},{"id":652800,"pid":650000,"initial":"B","name":"巴音郭楞"},{"id":652900,"pid":650000,"initial":"A","name":"阿克苏"},{"id":653000,"pid":650000,"initial":"K","name":"克孜勒苏柯尔克孜"},{"id":653100,"pid":650000,"initial":"K","name":"喀什"},{"id":653200,"pid":650000,"initial":"H","name":"和田"},{"id":654000,"pid":650000,"initial":"Y","name":"伊犁"},{"id":654200,"pid":650000,"initial":"T","name":"塔城"},{"id":654300,"pid":650000,"initial":"A","name":"阿勒泰"},{"id":659001,"pid":650000,"initial":"S","name":"石河子"},{"id":659002,"pid":650000,"initial":"A","name":"阿拉尔"},{"id":659003,"pid":650000,"initial":"T","name":"图木舒克"},{"id":659004,"pid":650000,"initial":"W","name":"五家渠"}]},{"id":710000,"pid":0,"initial":"T","name":"台湾","son":[{"id":710100,"pid":710000,"initial":"T","name":"台北"},{"id":710200,"pid":710000,"initial":"G","name":"高雄"},{"id":710300,"pid":710000,"initial":"T","name":"台南"},{"id":710400,"pid":710000,"initial":"T","name":"台中"},{"id":710500,"pid":710000,"initial":"J","name":"金门"},{"id":710600,"pid":710000,"initial":"N","name":"南投"},{"id":710700,"pid":710000,"initial":"J","name":"基隆"},{"id":710800,"pid":710000,"initial":"X","name":"新竹"},{"id":710900,"pid":710000,"initial":"J","name":"嘉义"},{"id":711100,"pid":710000,"initial":"T","name":"台北"},{"id":711200,"pid":710000,"initial":"Y","name":"宜兰"},{"id":711300,"pid":710000,"initial":"X","name":"新竹"},{"id":711400,"pid":710000,"initial":"T","name":"桃园"},{"id":711500,"pid":710000,"initial":"M","name":"苗栗"},{"id":711600,"pid":710000,"initial":"T","name":"台中"},{"id":711700,"pid":710000,"initial":"Z","name":"彰化"},{"id":711900,"pid":710000,"initial":"J","name":"嘉义"},{"id":712100,"pid":710000,"initial":"Y","name":"云林"},{"id":712200,"pid":710000,"initial":"T","name":"台南"},{"id":712300,"pid":710000,"initial":"G","name":"高雄"},{"id":712400,"pid":710000,"initial":"P","name":"屏东"},{"id":712500,"pid":710000,"initial":"T","name":"台东"},{"id":712600,"pid":710000,"initial":"H","name":"花莲"},{"id":712700,"pid":710000,"initial":"P","name":"澎湖"}]},{"id":810000,"pid":0,"initial":"X","name":"香港","son":[{"id":810100,"pid":810000,"initial":"X","name":"香港岛"},{"id":810200,"pid":810000,"initial":"J","name":"九龙"},{"id":810300,"pid":810000,"initial":"X","name":"新界"}]},{"id":820000,"pid":0,"initial":"A","name":"澳门","son":[{"id":820100,"pid":820000,"initial":"A","name":"澳门"},{"id":820200,"pid":820000,"initial":"L","name":"离岛"}]},{"id":990000,"pid":0,"initial":"H","name":"海外","son":[{"id":990100,"pid":990000,"initial":"H","name":"海外"}]}],"hot":[{"id":110100,"pid":110000,"initial":"B","name":"北京市"},{"id":120100,"pid":120000,"initial":"T","name":"天津市"},{"id":130100,"pid":130000,"initial":"S","name":"石家庄"},{"id":330100,"pid":330000,"initial":"H","name":"杭州"}]}
     * msg : 获取成功
     */

    private int code;
    private ResultBean result;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ResultBean {
        private List<CityBean> city;
        private List<HotBean> hot;

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public static class CityBean {
            /**
             * id : 110000
             * pid : 0
             * initial : B
             * name : 北京
             * son : [{"id":110100,"pid":110000,"initial":"B","name":"北京市"}]
             */

            private int id;
            private int pid;
            private String initial;
            private String name;
            private List<SonBean> son;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getInitial() {
                return initial;
            }

            public void setInitial(String initial) {
                this.initial = initial;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SonBean> getSon() {
                return son;
            }

            public void setSon(List<SonBean> son) {
                this.son = son;
            }


            public static class SonBean {
                /**
                 * id : 110100
                 * pid : 110000
                 * initial : B
                 * name : 北京市
                 */

                private int id;
                private int pid;
                private String initial;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getInitial() {
                    return initial;
                }

                public void setInitial(String initial) {
                    this.initial = initial;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class HotBean {
            /**
             * id : 110100
             * pid : 110000
             * initial : B
             * name : 北京市
             */

            private int id;
            private int pid;
            private String initial;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getInitial() {
                return initial;
            }

            public void setInitial(String initial) {
                this.initial = initial;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}

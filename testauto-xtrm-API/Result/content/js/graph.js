/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 200.0, "maxY": 3.0, "series": [{"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case16_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_UserWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case7_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case17_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_CompanyWallets", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case14_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case16_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_UserWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case10_ToUser", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case12_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case9_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case12_userwithdraw", "isController": false}, {"data": [[1100.0, 1.0], [800.0, 1.0], [900.0, 1.0]], "isOverall": false, "label": "Get Exchange Rate", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case1_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case24_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case1_tax", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case11_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case10_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case23_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case15_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case9_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case25_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case7_UpdateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case18_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_UserWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case13_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_UserWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case13_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_UserWallets", "isController": false}, {"data": [[1500.0, 1.0]], "isOverall": false, "label": "Key", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_UserWallets", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case21_ToUser", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case22_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case14_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case9_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case15_CreateUser", "isController": false}, {"data": [[300.0, 2.0], [200.0, 3.0], [400.0, 1.0]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case17_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case8_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case14_CreateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case22_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case10_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case8_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case13_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case9_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case8_UpdateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case26_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case4_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case2_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case18_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case10_CreateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case2_UpdateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case19_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_States", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_States", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case9_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet", "isController": false}, {"data": [[300.0, 1.0], [200.0, 1.0]], "isOverall": false, "label": "Error_case1_CompanyWallets", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case7_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case11_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case23_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case16_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case12_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case20_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case8_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case3_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_UpdateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case15_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_CompanyWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case15_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6_CreateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case11_userwithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case4_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case1_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case17_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case23_ToCompany", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case4_UpdateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case10_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case20_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case13_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case10_companywithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case8_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case15_ToUser", "isController": false}, {"data": [[200.0, 3.0], [1000.0, 1.0]], "isOverall": false, "label": "ValidateIBAN", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case25_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case11_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case2_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case13_companywithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case19_Advanced profile", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case11_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case18_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_ToCompany", "isController": false}, {"data": [[500.0, 1.0]], "isOverall": false, "label": "Error_case10_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case17_CreateUser", "isController": false}, {"data": [[700.0, 1.0]], "isOverall": false, "label": "Error_case7_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case8_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case10_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case21_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_CreateUser", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case22_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case2_companywithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case8_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case2_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case1_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case12_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case11_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6_CompanyWallets", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case11_ToCompany", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case8_companywithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case9_UpdateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case14_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_companywithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case14_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case4_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case6_userwithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case20_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case19_ToUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case4_CompanyWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_Advanced profile", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case16_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_UpdateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case20_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case3_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_States", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_States", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case24_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case17_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case9_CreateUser", "isController": false}, {"data": [[400.0, 1.0]], "isOverall": false, "label": "Error_case21_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case4_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case1_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case19_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case16_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case21_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case12_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case19_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case2_Programs", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case8_Advanced profile", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case7_CreateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case18_ToCompany", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_ToCompany", "isController": false}, {"data": [[300.0, 1.0], [200.0, 1.0]], "isOverall": false, "label": "Error_case1_UpdateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case12_companywithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case13_userwithdraw", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_CompanyWallets", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case7_card", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_tax", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case6_Programs", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case9_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case9_tax", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case18_card", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case3_UpdateUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case13_ToCompany", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case5_userwithdraw", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case12_card", "isController": false}, {"data": [[300.0, 3.0], [200.0, 3.0]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case5_CreateUser", "isController": false}, {"data": [[200.0, 1.0]], "isOverall": false, "label": "Error_case24_ToUser", "isController": false}, {"data": [[300.0, 1.0]], "isOverall": false, "label": "Error_case1_userwithdraw", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1500.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 171.0, "series": [{"data": [[0.0, 171.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 3.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 1.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [[3.0, 37.0]], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 3.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 1.0, "minX": 1.64681088E12, "maxY": 1.0, "series": [{"data": [[1.646810935E12, 1.0], [1.646810915E12, 1.0], [1.646810895E12, 1.0], [1.64681094E12, 1.0], [1.64681092E12, 1.0], [1.6468109E12, 1.0], [1.64681088E12, 1.0], [1.646810945E12, 1.0], [1.646810925E12, 1.0], [1.646810905E12, 1.0], [1.646810885E12, 1.0], [1.64681095E12, 1.0], [1.64681093E12, 1.0], [1.64681091E12, 1.0], [1.64681089E12, 1.0]], "isOverall": false, "label": "Valid cases", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 5000, "maxX": 1.64681095E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 271.0, "minX": 1.0, "maxY": 1586.0, "series": [{"data": [[1.0, 461.0]], "isOverall": false, "label": "Error_case16_ToCompany", "isController": false}, {"data": [[1.0, 461.0]], "isOverall": false, "label": "Error_case16_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case1_UserWallets", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case1_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 284.0]], "isOverall": false, "label": "Error_case5_ToUser", "isController": false}, {"data": [[1.0, 284.0]], "isOverall": false, "label": "Error_case5_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case7_ToUser", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case7_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case17_Advanced profile", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case17_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 292.0]], "isOverall": false, "label": "Error_case2_CompanyWallets", "isController": false}, {"data": [[1.0, 292.0]], "isOverall": false, "label": "Error_case2_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case14_ToUser", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case14_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case16_ToUser", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case16_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case2_UserWallets", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case2_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case10_ToUser", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case10_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 445.0]], "isOverall": false, "label": "Error_case12_ToUser", "isController": false}, {"data": [[1.0, 445.0]], "isOverall": false, "label": "Error_case12_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 287.0]], "isOverall": false, "label": "Error_case3_ToUser", "isController": false}, {"data": [[1.0, 287.0]], "isOverall": false, "label": "Error_case3_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case9_ToUser", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case9_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_userwithdraw", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 998.0]], "isOverall": false, "label": "Get Exchange Rate", "isController": false}, {"data": [[1.0, 998.0]], "isOverall": false, "label": "Get Exchange Rate-Aggregated", "isController": false}, {"data": [[1.0, 335.0]], "isOverall": false, "label": "Error_case1_ToUser", "isController": false}, {"data": [[1.0, 335.0]], "isOverall": false, "label": "Error_case1_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case24_ToCompany", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case24_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case1_tax", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case1_tax-Aggregated", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case5_Advanced profile", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case5_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 336.0]], "isOverall": false, "label": "Error_case11_card", "isController": false}, {"data": [[1.0, 336.0]], "isOverall": false, "label": "Error_case11_card-Aggregated", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case1_Advanced profile", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case1_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 311.0]], "isOverall": false, "label": "Error_case10_tax", "isController": false}, {"data": [[1.0, 311.0]], "isOverall": false, "label": "Error_case10_tax-Aggregated", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case23_Advanced profile", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case23_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 353.0]], "isOverall": false, "label": "Error_case15_ToCompany", "isController": false}, {"data": [[1.0, 353.0]], "isOverall": false, "label": "Error_case15_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 277.0]], "isOverall": false, "label": "Error_case3_companywithdraw", "isController": false}, {"data": [[1.0, 277.0]], "isOverall": false, "label": "Error_case3_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 351.0]], "isOverall": false, "label": "Error_case6_companywithdraw", "isController": false}, {"data": [[1.0, 351.0]], "isOverall": false, "label": "Error_case6_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case9_companywithdraw", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case9_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case25_ToCompany", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case25_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case7_UpdateUser", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case7_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 311.0]], "isOverall": false, "label": "Error_case18_ToUser", "isController": false}, {"data": [[1.0, 311.0]], "isOverall": false, "label": "Error_case18_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case6_UserWallets", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case6_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 282.0]], "isOverall": false, "label": "Error_case13_Advanced profile", "isController": false}, {"data": [[1.0, 282.0]], "isOverall": false, "label": "Error_case13_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case5_UserWallets", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case5_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case13_card", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case13_card-Aggregated", "isController": false}, {"data": [[1.0, 271.0]], "isOverall": false, "label": "Error_case3_UserWallets", "isController": false}, {"data": [[1.0, 271.0]], "isOverall": false, "label": "Error_case3_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 1586.0]], "isOverall": false, "label": "Key", "isController": false}, {"data": [[1.0, 1586.0]], "isOverall": false, "label": "Key-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case4_UserWallets", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case4_UserWallets-Aggregated", "isController": false}, {"data": [[1.0, 322.0]], "isOverall": false, "label": "Error_case21_ToUser", "isController": false}, {"data": [[1.0, 322.0]], "isOverall": false, "label": "Error_case21_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 404.0]], "isOverall": false, "label": "Error_case22_ToCompany", "isController": false}, {"data": [[1.0, 404.0]], "isOverall": false, "label": "Error_case22_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case14_ToCompany", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case14_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 337.0]], "isOverall": false, "label": "Error_case9_Advanced profile", "isController": false}, {"data": [[1.0, 337.0]], "isOverall": false, "label": "Error_case9_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case15_CreateUser", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case15_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 316.3333333333333]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter", "isController": false}, {"data": [[1.0, 316.3333333333333]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter-Aggregated", "isController": false}, {"data": [[1.0, 361.0]], "isOverall": false, "label": "Error_case6_Advanced profile", "isController": false}, {"data": [[1.0, 361.0]], "isOverall": false, "label": "Error_case6_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 395.0]], "isOverall": false, "label": "Error_case17_ToCompany", "isController": false}, {"data": [[1.0, 395.0]], "isOverall": false, "label": "Error_case17_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case8_userwithdraw", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case8_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 303.0]], "isOverall": false, "label": "Error_case14_CreateUser", "isController": false}, {"data": [[1.0, 303.0]], "isOverall": false, "label": "Error_case14_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 313.0]], "isOverall": false, "label": "Error_case22_Advanced profile", "isController": false}, {"data": [[1.0, 313.0]], "isOverall": false, "label": "Error_case22_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 320.0]], "isOverall": false, "label": "Error_case10_userwithdraw", "isController": false}, {"data": [[1.0, 320.0]], "isOverall": false, "label": "Error_case10_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case8_tax", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case8_tax-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case2_userwithdraw", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case2_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 323.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 323.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 304.0]], "isOverall": false, "label": "Error_case13_CreateUser", "isController": false}, {"data": [[1.0, 304.0]], "isOverall": false, "label": "Error_case13_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case9_ToCompany", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case9_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case5_card", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case5_card-Aggregated", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case8_UpdateUser", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case8_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case26_Advanced profile", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case26_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 367.0]], "isOverall": false, "label": "Error_case4_Advanced profile", "isController": false}, {"data": [[1.0, 367.0]], "isOverall": false, "label": "Error_case4_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 333.0]], "isOverall": false, "label": "Error_case2_tax", "isController": false}, {"data": [[1.0, 333.0]], "isOverall": false, "label": "Error_case2_tax-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case18_Advanced profile", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case18_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case10_CreateUser", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case10_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case2_UpdateUser", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case2_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case19_CreateUser", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case19_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 298.0]], "isOverall": false, "label": "Error_case3_States", "isController": false}, {"data": [[1.0, 298.0]], "isOverall": false, "label": "Error_case3_States-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case6_States", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case6_States-Aggregated", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case9_card", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case9_card-Aggregated", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 289.5]], "isOverall": false, "label": "Error_case1_CompanyWallets", "isController": false}, {"data": [[1.0, 289.5]], "isOverall": false, "label": "Error_case1_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 361.0]], "isOverall": false, "label": "Error_case7_ToCompany", "isController": false}, {"data": [[1.0, 361.0]], "isOverall": false, "label": "Error_case7_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case7_Programs", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case7_Programs-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case11_companywithdraw", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case11_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case23_ToUser", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case23_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case16_card", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case16_card-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case4_CreateUser", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case4_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_Advanced profile", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case4_Programs", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case4_Programs-Aggregated", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case20_Advanced profile", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case20_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case8_ToCompany", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case8_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 345.0]], "isOverall": false, "label": "Error_case3_card", "isController": false}, {"data": [[1.0, 345.0]], "isOverall": false, "label": "Error_case3_card-Aggregated", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case1_Programs", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case1_Programs-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case6_UpdateUser", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case6_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case7_userwithdraw", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case7_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case15_card", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case15_card-Aggregated", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case2_ToCompany", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case2_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case5_CompanyWallets", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case5_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case15_Advanced profile", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case15_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case3_userwithdraw", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case3_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 302.0]], "isOverall": false, "label": "Error_case6_CreateUser", "isController": false}, {"data": [[1.0, 302.0]], "isOverall": false, "label": "Error_case6_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 326.0]], "isOverall": false, "label": "Error_case11_userwithdraw", "isController": false}, {"data": [[1.0, 326.0]], "isOverall": false, "label": "Error_case11_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case4_companywithdraw", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case4_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case1_companywithdraw", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case1_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 319.0]], "isOverall": false, "label": "Error_case17_ToUser", "isController": false}, {"data": [[1.0, 319.0]], "isOverall": false, "label": "Error_case17_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 355.0]], "isOverall": false, "label": "Error_case23_ToCompany", "isController": false}, {"data": [[1.0, 355.0]], "isOverall": false, "label": "Error_case23_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 441.0]], "isOverall": false, "label": "Error_case4_UpdateUser", "isController": false}, {"data": [[1.0, 441.0]], "isOverall": false, "label": "Error_case4_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case10_ToCompany", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case10_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 322.0]], "isOverall": false, "label": "Error_case7_companywithdraw", "isController": false}, {"data": [[1.0, 322.0]], "isOverall": false, "label": "Error_case7_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 318.0]], "isOverall": false, "label": "Error_case20_ToUser", "isController": false}, {"data": [[1.0, 318.0]], "isOverall": false, "label": "Error_case20_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case7_tax", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case7_tax-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case13_ToUser", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case13_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case3_ToCompany", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case3_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 357.0]], "isOverall": false, "label": "Error_case10_companywithdraw", "isController": false}, {"data": [[1.0, 357.0]], "isOverall": false, "label": "Error_case10_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case6_ToUser", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case6_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case8_ToUser", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case8_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case15_ToUser", "isController": false}, {"data": [[1.0, 314.0]], "isOverall": false, "label": "Error_case15_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 468.75]], "isOverall": false, "label": "ValidateIBAN", "isController": false}, {"data": [[1.0, 468.75]], "isOverall": false, "label": "ValidateIBAN-Aggregated", "isController": false}, {"data": [[1.0, 284.0]], "isOverall": false, "label": "Error_case25_Advanced profile", "isController": false}, {"data": [[1.0, 284.0]], "isOverall": false, "label": "Error_case25_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case11_ToUser", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case11_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax-Aggregated", "isController": false}, {"data": [[1.0, 339.0]], "isOverall": false, "label": "Error_case2_ToUser", "isController": false}, {"data": [[1.0, 339.0]], "isOverall": false, "label": "Error_case2_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case4_ToUser", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case4_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case6_card", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case6_card-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case13_companywithdraw", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case13_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 298.0]], "isOverall": false, "label": "Error_case19_Advanced profile", "isController": false}, {"data": [[1.0, 298.0]], "isOverall": false, "label": "Error_case19_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 470.0]], "isOverall": false, "label": "Error_case11_CreateUser", "isController": false}, {"data": [[1.0, 470.0]], "isOverall": false, "label": "Error_case11_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case2_CreateUser", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case2_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case18_CreateUser", "isController": false}, {"data": [[1.0, 281.0]], "isOverall": false, "label": "Error_case18_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case4_ToCompany", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case4_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 538.0]], "isOverall": false, "label": "Error_case10_Advanced profile", "isController": false}, {"data": [[1.0, 538.0]], "isOverall": false, "label": "Error_case10_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case4_card", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case4_card-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case3_tax", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case3_tax-Aggregated", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case17_CreateUser", "isController": false}, {"data": [[1.0, 279.0]], "isOverall": false, "label": "Error_case17_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 700.0]], "isOverall": false, "label": "Error_case7_Advanced profile", "isController": false}, {"data": [[1.0, 700.0]], "isOverall": false, "label": "Error_case7_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case8_CreateUser", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case8_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 277.0]], "isOverall": false, "label": "Error_case10_card", "isController": false}, {"data": [[1.0, 277.0]], "isOverall": false, "label": "Error_case10_card-Aggregated", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case21_Advanced profile", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case21_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case1_CreateUser", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case1_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 481.0]], "isOverall": false, "label": "Error_case22_ToUser", "isController": false}, {"data": [[1.0, 481.0]], "isOverall": false, "label": "Error_case22_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case2_companywithdraw", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case2_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case8_card", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case8_card-Aggregated", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case2_card", "isController": false}, {"data": [[1.0, 307.0]], "isOverall": false, "label": "Error_case2_card-Aggregated", "isController": false}, {"data": [[1.0, 351.0]], "isOverall": false, "label": "Error_case1_ToCompany", "isController": false}, {"data": [[1.0, 351.0]], "isOverall": false, "label": "Error_case1_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case12_CreateUser", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case12_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case11_Advanced profile", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case11_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 301.0]], "isOverall": false, "label": "Error_case6_CompanyWallets", "isController": false}, {"data": [[1.0, 301.0]], "isOverall": false, "label": "Error_case6_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 327.0]], "isOverall": false, "label": "Error_case11_ToCompany", "isController": false}, {"data": [[1.0, 327.0]], "isOverall": false, "label": "Error_case11_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 418.0]], "isOverall": false, "label": "Error_case8_companywithdraw", "isController": false}, {"data": [[1.0, 418.0]], "isOverall": false, "label": "Error_case8_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case9_UpdateUser", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case9_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case14_card", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case14_card-Aggregated", "isController": false}, {"data": [[1.0, 320.0]], "isOverall": false, "label": "Error_case5_companywithdraw", "isController": false}, {"data": [[1.0, 320.0]], "isOverall": false, "label": "Error_case5_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case14_Advanced profile", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case14_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case4_userwithdraw", "isController": false}, {"data": [[1.0, 312.0]], "isOverall": false, "label": "Error_case4_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case6_userwithdraw", "isController": false}, {"data": [[1.0, 356.0]], "isOverall": false, "label": "Error_case6_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case6_ToCompany", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case6_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case20_card", "isController": false}, {"data": [[1.0, 286.0]], "isOverall": false, "label": "Error_case20_card-Aggregated", "isController": false}, {"data": [[1.0, 350.0]], "isOverall": false, "label": "Error_case19_ToUser", "isController": false}, {"data": [[1.0, 350.0]], "isOverall": false, "label": "Error_case19_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case4_CompanyWallets", "isController": false}, {"data": [[1.0, 280.0]], "isOverall": false, "label": "Error_case4_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case5_Programs", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case5_Programs-Aggregated", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case3_Advanced profile", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case3_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 303.0]], "isOverall": false, "label": "Error_case16_CreateUser", "isController": false}, {"data": [[1.0, 303.0]], "isOverall": false, "label": "Error_case16_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 291.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 313.0]], "isOverall": false, "label": "Error_case5_UpdateUser", "isController": false}, {"data": [[1.0, 313.0]], "isOverall": false, "label": "Error_case5_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case20_ToCompany", "isController": false}, {"data": [[1.0, 344.0]], "isOverall": false, "label": "Error_case20_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case3_CreateUser", "isController": false}, {"data": [[1.0, 310.0]], "isOverall": false, "label": "Error_case3_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 292.0]], "isOverall": false, "label": "Error_case1_States", "isController": false}, {"data": [[1.0, 292.0]], "isOverall": false, "label": "Error_case1_States-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case2_States", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case2_States-Aggregated", "isController": false}, {"data": [[1.0, 296.0]], "isOverall": false, "label": "Error_case24_Advanced profile", "isController": false}, {"data": [[1.0, 296.0]], "isOverall": false, "label": "Error_case24_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case17_card", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case17_card-Aggregated", "isController": false}, {"data": [[1.0, 304.0]], "isOverall": false, "label": "Error_case9_CreateUser", "isController": false}, {"data": [[1.0, 304.0]], "isOverall": false, "label": "Error_case9_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 442.0]], "isOverall": false, "label": "Error_case21_ToCompany", "isController": false}, {"data": [[1.0, 442.0]], "isOverall": false, "label": "Error_case21_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case4_tax", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case4_tax-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case3_Programs", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case3_Programs-Aggregated", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case1_card", "isController": false}, {"data": [[1.0, 295.0]], "isOverall": false, "label": "Error_case1_card-Aggregated", "isController": false}, {"data": [[1.0, 346.0]], "isOverall": false, "label": "Error_case19_ToCompany", "isController": false}, {"data": [[1.0, 346.0]], "isOverall": false, "label": "Error_case19_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case2_Advanced profile", "isController": false}, {"data": [[1.0, 285.0]], "isOverall": false, "label": "Error_case2_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case16_Advanced profile", "isController": false}, {"data": [[1.0, 283.0]], "isOverall": false, "label": "Error_case16_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case21_card", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case21_card-Aggregated", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_ToCompany", "isController": false}, {"data": [[1.0, 290.0]], "isOverall": false, "label": "Error_case12_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case19_card", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case19_card-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case2_Programs", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case2_Programs-Aggregated", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case8_Advanced profile", "isController": false}, {"data": [[1.0, 317.0]], "isOverall": false, "label": "Error_case8_Advanced profile-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet-Aggregated", "isController": false}, {"data": [[1.0, 335.0]], "isOverall": false, "label": "Error_case7_CreateUser", "isController": false}, {"data": [[1.0, 335.0]], "isOverall": false, "label": "Error_case7_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 359.0]], "isOverall": false, "label": "Error_case18_ToCompany", "isController": false}, {"data": [[1.0, 359.0]], "isOverall": false, "label": "Error_case18_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case5_ToCompany", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case5_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 339.0]], "isOverall": false, "label": "Error_case1_UpdateUser", "isController": false}, {"data": [[1.0, 339.0]], "isOverall": false, "label": "Error_case1_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case12_companywithdraw", "isController": false}, {"data": [[1.0, 273.0]], "isOverall": false, "label": "Error_case12_companywithdraw-Aggregated", "isController": false}, {"data": [[1.0, 357.0]], "isOverall": false, "label": "Error_case13_userwithdraw", "isController": false}, {"data": [[1.0, 357.0]], "isOverall": false, "label": "Error_case13_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case3_CompanyWallets", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case3_CompanyWallets-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case7_card", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case7_card-Aggregated", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case5_tax", "isController": false}, {"data": [[1.0, 308.0]], "isOverall": false, "label": "Error_case5_tax-Aggregated", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case6_Programs", "isController": false}, {"data": [[1.0, 272.0]], "isOverall": false, "label": "Error_case6_Programs-Aggregated", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case9_userwithdraw", "isController": false}, {"data": [[1.0, 274.0]], "isOverall": false, "label": "Error_case9_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 329.0]], "isOverall": false, "label": "Error_case9_tax", "isController": false}, {"data": [[1.0, 329.0]], "isOverall": false, "label": "Error_case9_tax-Aggregated", "isController": false}, {"data": [[1.0, 329.0]], "isOverall": false, "label": "Error_case18_card", "isController": false}, {"data": [[1.0, 329.0]], "isOverall": false, "label": "Error_case18_card-Aggregated", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case3_UpdateUser", "isController": false}, {"data": [[1.0, 275.0]], "isOverall": false, "label": "Error_case3_UpdateUser-Aggregated", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case13_ToCompany", "isController": false}, {"data": [[1.0, 332.0]], "isOverall": false, "label": "Error_case13_ToCompany-Aggregated", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case5_userwithdraw", "isController": false}, {"data": [[1.0, 331.0]], "isOverall": false, "label": "Error_case5_userwithdraw-Aggregated", "isController": false}, {"data": [[1.0, 392.0]], "isOverall": false, "label": "Error_case12_card", "isController": false}, {"data": [[1.0, 392.0]], "isOverall": false, "label": "Error_case12_card-Aggregated", "isController": false}, {"data": [[1.0, 315.33333333333337]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter", "isController": false}, {"data": [[1.0, 315.33333333333337]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter-Aggregated", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case5_CreateUser", "isController": false}, {"data": [[1.0, 276.0]], "isOverall": false, "label": "Error_case5_CreateUser-Aggregated", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case24_ToUser", "isController": false}, {"data": [[1.0, 278.0]], "isOverall": false, "label": "Error_case24_ToUser-Aggregated", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case1_userwithdraw", "isController": false}, {"data": [[1.0, 316.0]], "isOverall": false, "label": "Error_case1_userwithdraw-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 1.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 116.4, "minX": 1.64681088E12, "maxY": 8243.2, "series": [{"data": [[1.646810935E12, 2874.2], [1.646810915E12, 2596.8], [1.646810895E12, 2842.6], [1.64681094E12, 1844.2], [1.64681092E12, 2836.4], [1.6468109E12, 3108.8], [1.64681088E12, 241.0], [1.646810945E12, 2350.0], [1.646810925E12, 3245.2], [1.646810905E12, 2866.2], [1.646810885E12, 2850.8], [1.64681095E12, 2602.2], [1.64681093E12, 3078.2], [1.64681091E12, 2862.6], [1.64681089E12, 2920.8]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.646810935E12, 2600.2], [1.646810915E12, 2744.2], [1.646810895E12, 7721.8], [1.64681094E12, 1833.6], [1.64681092E12, 3874.8], [1.6468109E12, 5594.0], [1.64681088E12, 116.4], [1.646810945E12, 7254.6], [1.646810925E12, 3374.2], [1.646810905E12, 4145.8], [1.646810885E12, 4231.0], [1.64681095E12, 8243.2], [1.64681093E12, 2636.8], [1.64681091E12, 3475.0], [1.64681089E12, 4932.2]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 5000, "maxX": 1.64681095E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 271.0, "minX": 1.64681088E12, "maxY": 1586.0, "series": [{"data": [[1.646810905E12, 461.0]], "isOverall": false, "label": "Error_case16_ToCompany", "isController": false}, {"data": [[1.646810925E12, 276.0]], "isOverall": false, "label": "Error_case1_UserWallets", "isController": false}, {"data": [[1.646810895E12, 284.0]], "isOverall": false, "label": "Error_case5_ToUser", "isController": false}, {"data": [[1.646810895E12, 274.0]], "isOverall": false, "label": "Error_case7_ToUser", "isController": false}, {"data": [[1.64681095E12, 295.0]], "isOverall": false, "label": "Error_case17_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 292.0]], "isOverall": false, "label": "Error_case2_CompanyWallets", "isController": false}, {"data": [[1.646810895E12, 317.0]], "isOverall": false, "label": "Error_case14_ToUser", "isController": false}, {"data": [[1.646810895E12, 316.0]], "isOverall": false, "label": "Error_case16_ToUser", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case2_UserWallets", "isController": false}, {"data": [[1.646810895E12, 275.0]], "isOverall": false, "label": "Error_case10_ToUser", "isController": false}, {"data": [[1.646810895E12, 445.0]], "isOverall": false, "label": "Error_case12_ToUser", "isController": false}, {"data": [[1.64681089E12, 287.0]], "isOverall": false, "label": "Error_case3_ToUser", "isController": false}, {"data": [[1.646810895E12, 281.0]], "isOverall": false, "label": "Error_case9_ToUser", "isController": false}, {"data": [[1.64681091E12, 290.0]], "isOverall": false, "label": "Error_case12_userwithdraw", "isController": false}, {"data": [[1.646810945E12, 899.0], [1.64681094E12, 1047.5]], "isOverall": false, "label": "Get Exchange Rate", "isController": false}, {"data": [[1.64681089E12, 335.0]], "isOverall": false, "label": "Error_case1_ToUser", "isController": false}, {"data": [[1.646810905E12, 356.0]], "isOverall": false, "label": "Error_case24_ToCompany", "isController": false}, {"data": [[1.64681093E12, 312.0]], "isOverall": false, "label": "Error_case1_tax", "isController": false}, {"data": [[1.646810945E12, 308.0]], "isOverall": false, "label": "Error_case5_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 336.0]], "isOverall": false, "label": "Error_case11_card", "isController": false}, {"data": [[1.646810945E12, 286.0]], "isOverall": false, "label": "Error_case1_Advanced profile", "isController": false}, {"data": [[1.646810935E12, 311.0]], "isOverall": false, "label": "Error_case10_tax", "isController": false}, {"data": [[1.64681095E12, 283.0]], "isOverall": false, "label": "Error_case23_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 353.0]], "isOverall": false, "label": "Error_case15_ToCompany", "isController": false}, {"data": [[1.646810915E12, 277.0]], "isOverall": false, "label": "Error_case3_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 351.0]], "isOverall": false, "label": "Error_case6_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 312.0]], "isOverall": false, "label": "Error_case9_companywithdraw", "isController": false}, {"data": [[1.646810905E12, 308.0]], "isOverall": false, "label": "Error_case25_ToCompany", "isController": false}, {"data": [[1.64681089E12, 276.0]], "isOverall": false, "label": "Error_case7_UpdateUser", "isController": false}, {"data": [[1.646810895E12, 311.0]], "isOverall": false, "label": "Error_case18_ToUser", "isController": false}, {"data": [[1.646810925E12, 275.0]], "isOverall": false, "label": "Error_case6_UserWallets", "isController": false}, {"data": [[1.64681095E12, 282.0]], "isOverall": false, "label": "Error_case13_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 278.0]], "isOverall": false, "label": "Error_case5_UserWallets", "isController": false}, {"data": [[1.64681092E12, 276.0]], "isOverall": false, "label": "Error_case13_card", "isController": false}, {"data": [[1.646810925E12, 271.0]], "isOverall": false, "label": "Error_case3_UserWallets", "isController": false}, {"data": [[1.64681088E12, 1586.0]], "isOverall": false, "label": "Key", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case4_UserWallets", "isController": false}, {"data": [[1.6468109E12, 322.0]], "isOverall": false, "label": "Error_case21_ToUser", "isController": false}, {"data": [[1.646810905E12, 404.0]], "isOverall": false, "label": "Error_case22_ToCompany", "isController": false}, {"data": [[1.646810905E12, 317.0]], "isOverall": false, "label": "Error_case14_ToCompany", "isController": false}, {"data": [[1.646810945E12, 337.0]], "isOverall": false, "label": "Error_case9_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 314.0]], "isOverall": false, "label": "Error_case15_CreateUser", "isController": false}, {"data": [[1.646810935E12, 300.0], [1.64681094E12, 349.0]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810945E12, 361.0]], "isOverall": false, "label": "Error_case6_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 395.0]], "isOverall": false, "label": "Error_case17_ToCompany", "isController": false}, {"data": [[1.64681091E12, 316.0]], "isOverall": false, "label": "Error_case8_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 303.0]], "isOverall": false, "label": "Error_case14_CreateUser", "isController": false}, {"data": [[1.64681095E12, 313.0]], "isOverall": false, "label": "Error_case22_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 320.0]], "isOverall": false, "label": "Error_case10_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 312.0]], "isOverall": false, "label": "Error_case8_tax", "isController": false}, {"data": [[1.64681091E12, 281.0]], "isOverall": false, "label": "Error_case2_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 323.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 304.0]], "isOverall": false, "label": "Error_case13_CreateUser", "isController": false}, {"data": [[1.6468109E12, 279.0]], "isOverall": false, "label": "Error_case9_ToCompany", "isController": false}, {"data": [[1.64681092E12, 274.0]], "isOverall": false, "label": "Error_case5_card", "isController": false}, {"data": [[1.64681089E12, 295.0]], "isOverall": false, "label": "Error_case8_UpdateUser", "isController": false}, {"data": [[1.64681095E12, 281.0]], "isOverall": false, "label": "Error_case26_Advanced profile", "isController": false}, {"data": [[1.646810945E12, 367.0]], "isOverall": false, "label": "Error_case4_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 333.0]], "isOverall": false, "label": "Error_case2_tax", "isController": false}, {"data": [[1.64681095E12, 281.0]], "isOverall": false, "label": "Error_case18_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 344.0]], "isOverall": false, "label": "Error_case10_CreateUser", "isController": false}, {"data": [[1.64681089E12, 308.0]], "isOverall": false, "label": "Error_case2_UpdateUser", "isController": false}, {"data": [[1.64681089E12, 276.0]], "isOverall": false, "label": "Error_case19_CreateUser", "isController": false}, {"data": [[1.646810935E12, 298.0]], "isOverall": false, "label": "Error_case3_States", "isController": false}, {"data": [[1.646810935E12, 278.0]], "isOverall": false, "label": "Error_case6_States", "isController": false}, {"data": [[1.64681092E12, 307.0]], "isOverall": false, "label": "Error_case9_card", "isController": false}, {"data": [[1.64681093E12, 344.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet", "isController": false}, {"data": [[1.64681093E12, 289.5]], "isOverall": false, "label": "Error_case1_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 361.0]], "isOverall": false, "label": "Error_case7_ToCompany", "isController": false}, {"data": [[1.64681093E12, 283.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case7_Programs", "isController": false}, {"data": [[1.646810915E12, 273.0]], "isOverall": false, "label": "Error_case11_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 314.0]], "isOverall": false, "label": "Error_case23_ToUser", "isController": false}, {"data": [[1.646810925E12, 285.0]], "isOverall": false, "label": "Error_case16_card", "isController": false}, {"data": [[1.646810885E12, 279.0]], "isOverall": false, "label": "Error_case4_CreateUser", "isController": false}, {"data": [[1.646810945E12, 290.0]], "isOverall": false, "label": "Error_case12_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 273.0]], "isOverall": false, "label": "Error_case4_Programs", "isController": false}, {"data": [[1.64681095E12, 291.0]], "isOverall": false, "label": "Error_case20_Advanced profile", "isController": false}, {"data": [[1.6468109E12, 275.0]], "isOverall": false, "label": "Error_case8_ToCompany", "isController": false}, {"data": [[1.64681092E12, 345.0]], "isOverall": false, "label": "Error_case3_card", "isController": false}, {"data": [[1.646810925E12, 280.0]], "isOverall": false, "label": "Error_case1_Programs", "isController": false}, {"data": [[1.64681089E12, 278.0]], "isOverall": false, "label": "Error_case6_UpdateUser", "isController": false}, {"data": [[1.64681091E12, 332.0]], "isOverall": false, "label": "Error_case7_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 331.0]], "isOverall": false, "label": "Error_case15_card", "isController": false}, {"data": [[1.6468109E12, 280.0]], "isOverall": false, "label": "Error_case2_ToCompany", "isController": false}, {"data": [[1.64681093E12, 273.0]], "isOverall": false, "label": "Error_case5_CompanyWallets", "isController": false}, {"data": [[1.64681095E12, 281.0]], "isOverall": false, "label": "Error_case15_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 279.0]], "isOverall": false, "label": "Error_case3_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 302.0]], "isOverall": false, "label": "Error_case6_CreateUser", "isController": false}, {"data": [[1.64681091E12, 326.0]], "isOverall": false, "label": "Error_case11_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 274.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet", "isController": false}, {"data": [[1.646810915E12, 317.0]], "isOverall": false, "label": "Error_case4_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 310.0]], "isOverall": false, "label": "Error_case1_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 319.0]], "isOverall": false, "label": "Error_case17_ToUser", "isController": false}, {"data": [[1.646810905E12, 355.0]], "isOverall": false, "label": "Error_case23_ToCompany", "isController": false}, {"data": [[1.64681089E12, 441.0]], "isOverall": false, "label": "Error_case4_UpdateUser", "isController": false}, {"data": [[1.6468109E12, 273.0]], "isOverall": false, "label": "Error_case10_ToCompany", "isController": false}, {"data": [[1.646810915E12, 322.0]], "isOverall": false, "label": "Error_case7_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 318.0]], "isOverall": false, "label": "Error_case20_ToUser", "isController": false}, {"data": [[1.646810935E12, 310.0]], "isOverall": false, "label": "Error_case7_tax", "isController": false}, {"data": [[1.646810895E12, 279.0]], "isOverall": false, "label": "Error_case13_ToUser", "isController": false}, {"data": [[1.6468109E12, 273.0]], "isOverall": false, "label": "Error_case3_ToCompany", "isController": false}, {"data": [[1.646810915E12, 357.0]], "isOverall": false, "label": "Error_case10_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 279.0]], "isOverall": false, "label": "Error_case6_ToUser", "isController": false}, {"data": [[1.646810895E12, 274.0]], "isOverall": false, "label": "Error_case8_ToUser", "isController": false}, {"data": [[1.646810895E12, 314.0]], "isOverall": false, "label": "Error_case15_ToUser", "isController": false}, {"data": [[1.646810915E12, 532.3333333333334], [1.64681092E12, 278.0]], "isOverall": false, "label": "ValidateIBAN", "isController": false}, {"data": [[1.64681095E12, 284.0]], "isOverall": false, "label": "Error_case25_Advanced profile", "isController": false}, {"data": [[1.646810895E12, 275.0]], "isOverall": false, "label": "Error_case11_ToUser", "isController": false}, {"data": [[1.646810935E12, 356.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax", "isController": false}, {"data": [[1.64681089E12, 339.0]], "isOverall": false, "label": "Error_case2_ToUser", "isController": false}, {"data": [[1.646810895E12, 276.0]], "isOverall": false, "label": "Error_case4_ToUser", "isController": false}, {"data": [[1.64681092E12, 280.0]], "isOverall": false, "label": "Error_case6_card", "isController": false}, {"data": [[1.646810915E12, 274.0]], "isOverall": false, "label": "Error_case13_companywithdraw", "isController": false}, {"data": [[1.64681095E12, 298.0]], "isOverall": false, "label": "Error_case19_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 470.0]], "isOverall": false, "label": "Error_case11_CreateUser", "isController": false}, {"data": [[1.646810885E12, 291.0]], "isOverall": false, "label": "Error_case2_CreateUser", "isController": false}, {"data": [[1.64681089E12, 281.0]], "isOverall": false, "label": "Error_case18_CreateUser", "isController": false}, {"data": [[1.6468109E12, 273.0]], "isOverall": false, "label": "Error_case4_ToCompany", "isController": false}, {"data": [[1.646810945E12, 538.0]], "isOverall": false, "label": "Error_case10_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 274.0]], "isOverall": false, "label": "Error_case4_card", "isController": false}, {"data": [[1.646810935E12, 273.0]], "isOverall": false, "label": "Error_case3_tax", "isController": false}, {"data": [[1.64681089E12, 279.0]], "isOverall": false, "label": "Error_case17_CreateUser", "isController": false}, {"data": [[1.646810945E12, 700.0]], "isOverall": false, "label": "Error_case7_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 331.0]], "isOverall": false, "label": "Error_case8_CreateUser", "isController": false}, {"data": [[1.64681092E12, 277.0]], "isOverall": false, "label": "Error_case10_card", "isController": false}, {"data": [[1.64681095E12, 307.0]], "isOverall": false, "label": "Error_case21_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 278.0]], "isOverall": false, "label": "Error_case1_CreateUser", "isController": false}, {"data": [[1.6468109E12, 481.0]], "isOverall": false, "label": "Error_case22_ToUser", "isController": false}, {"data": [[1.64681091E12, 310.0]], "isOverall": false, "label": "Error_case2_companywithdraw", "isController": false}, {"data": [[1.64681092E12, 276.0]], "isOverall": false, "label": "Error_case8_card", "isController": false}, {"data": [[1.64681092E12, 307.0]], "isOverall": false, "label": "Error_case2_card", "isController": false}, {"data": [[1.6468109E12, 351.0]], "isOverall": false, "label": "Error_case1_ToCompany", "isController": false}, {"data": [[1.646810885E12, 331.0]], "isOverall": false, "label": "Error_case12_CreateUser", "isController": false}, {"data": [[1.646810945E12, 285.0]], "isOverall": false, "label": "Error_case11_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 301.0]], "isOverall": false, "label": "Error_case6_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 327.0]], "isOverall": false, "label": "Error_case11_ToCompany", "isController": false}, {"data": [[1.646810915E12, 418.0]], "isOverall": false, "label": "Error_case8_companywithdraw", "isController": false}, {"data": [[1.64681089E12, 283.0]], "isOverall": false, "label": "Error_case9_UpdateUser", "isController": false}, {"data": [[1.64681092E12, 291.0]], "isOverall": false, "label": "Error_case14_card", "isController": false}, {"data": [[1.646810915E12, 320.0]], "isOverall": false, "label": "Error_case5_companywithdraw", "isController": false}, {"data": [[1.64681093E12, 283.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet", "isController": false}, {"data": [[1.64681095E12, 286.0]], "isOverall": false, "label": "Error_case14_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 312.0]], "isOverall": false, "label": "Error_case4_userwithdraw", "isController": false}, {"data": [[1.64681091E12, 356.0]], "isOverall": false, "label": "Error_case6_userwithdraw", "isController": false}, {"data": [[1.6468109E12, 274.0]], "isOverall": false, "label": "Error_case6_ToCompany", "isController": false}, {"data": [[1.646810925E12, 286.0]], "isOverall": false, "label": "Error_case20_card", "isController": false}, {"data": [[1.646810895E12, 350.0]], "isOverall": false, "label": "Error_case19_ToUser", "isController": false}, {"data": [[1.64681093E12, 280.0]], "isOverall": false, "label": "Error_case4_CompanyWallets", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case5_Programs", "isController": false}, {"data": [[1.646810945E12, 285.0]], "isOverall": false, "label": "Error_case3_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 303.0]], "isOverall": false, "label": "Error_case16_CreateUser", "isController": false}, {"data": [[1.64681093E12, 291.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet", "isController": false}, {"data": [[1.64681089E12, 313.0]], "isOverall": false, "label": "Error_case5_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 344.0]], "isOverall": false, "label": "Error_case20_ToCompany", "isController": false}, {"data": [[1.646810885E12, 310.0]], "isOverall": false, "label": "Error_case3_CreateUser", "isController": false}, {"data": [[1.646810935E12, 292.0]], "isOverall": false, "label": "Error_case1_States", "isController": false}, {"data": [[1.646810935E12, 278.0]], "isOverall": false, "label": "Error_case2_States", "isController": false}, {"data": [[1.64681095E12, 296.0]], "isOverall": false, "label": "Error_case24_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 273.0]], "isOverall": false, "label": "Error_case17_card", "isController": false}, {"data": [[1.646810885E12, 304.0]], "isOverall": false, "label": "Error_case9_CreateUser", "isController": false}, {"data": [[1.646810905E12, 442.0]], "isOverall": false, "label": "Error_case21_ToCompany", "isController": false}, {"data": [[1.646810935E12, 332.0]], "isOverall": false, "label": "Error_case4_tax", "isController": false}, {"data": [[1.646810925E12, 276.0]], "isOverall": false, "label": "Error_case3_Programs", "isController": false}, {"data": [[1.64681092E12, 295.0]], "isOverall": false, "label": "Error_case1_card", "isController": false}, {"data": [[1.646810905E12, 346.0]], "isOverall": false, "label": "Error_case19_ToCompany", "isController": false}, {"data": [[1.646810945E12, 285.0]], "isOverall": false, "label": "Error_case2_Advanced profile", "isController": false}, {"data": [[1.64681095E12, 283.0]], "isOverall": false, "label": "Error_case16_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case21_card", "isController": false}, {"data": [[1.646810905E12, 290.0]], "isOverall": false, "label": "Error_case12_ToCompany", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case19_card", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case2_Programs", "isController": false}, {"data": [[1.646810945E12, 317.0]], "isOverall": false, "label": "Error_case8_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 272.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 335.0]], "isOverall": false, "label": "Error_case7_CreateUser", "isController": false}, {"data": [[1.646810905E12, 359.0]], "isOverall": false, "label": "Error_case18_ToCompany", "isController": false}, {"data": [[1.6468109E12, 275.0]], "isOverall": false, "label": "Error_case5_ToCompany", "isController": false}, {"data": [[1.64681089E12, 339.0]], "isOverall": false, "label": "Error_case1_UpdateUser", "isController": false}, {"data": [[1.646810915E12, 273.0]], "isOverall": false, "label": "Error_case12_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 357.0]], "isOverall": false, "label": "Error_case13_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 272.0]], "isOverall": false, "label": "Error_case3_CompanyWallets", "isController": false}, {"data": [[1.64681092E12, 278.0]], "isOverall": false, "label": "Error_case7_card", "isController": false}, {"data": [[1.646810935E12, 308.0]], "isOverall": false, "label": "Error_case5_tax", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case6_Programs", "isController": false}, {"data": [[1.64681091E12, 274.0]], "isOverall": false, "label": "Error_case9_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 329.0]], "isOverall": false, "label": "Error_case9_tax", "isController": false}, {"data": [[1.646810925E12, 329.0]], "isOverall": false, "label": "Error_case18_card", "isController": false}, {"data": [[1.64681089E12, 275.0]], "isOverall": false, "label": "Error_case3_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 332.0]], "isOverall": false, "label": "Error_case13_ToCompany", "isController": false}, {"data": [[1.64681091E12, 331.0]], "isOverall": false, "label": "Error_case5_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 392.0]], "isOverall": false, "label": "Error_case12_card", "isController": false}, {"data": [[1.64681094E12, 315.33333333333337]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810885E12, 276.0]], "isOverall": false, "label": "Error_case5_CreateUser", "isController": false}, {"data": [[1.6468109E12, 278.0]], "isOverall": false, "label": "Error_case24_ToUser", "isController": false}, {"data": [[1.64681091E12, 316.0]], "isOverall": false, "label": "Error_case1_userwithdraw", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 5000, "maxX": 1.64681095E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 270.0, "minX": 1.64681088E12, "maxY": 1581.0, "series": [{"data": [[1.646810905E12, 461.0]], "isOverall": false, "label": "Error_case16_ToCompany", "isController": false}, {"data": [[1.646810925E12, 275.0]], "isOverall": false, "label": "Error_case1_UserWallets", "isController": false}, {"data": [[1.646810895E12, 284.0]], "isOverall": false, "label": "Error_case5_ToUser", "isController": false}, {"data": [[1.646810895E12, 273.0]], "isOverall": false, "label": "Error_case7_ToUser", "isController": false}, {"data": [[1.64681095E12, 295.0]], "isOverall": false, "label": "Error_case17_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 292.0]], "isOverall": false, "label": "Error_case2_CompanyWallets", "isController": false}, {"data": [[1.646810895E12, 317.0]], "isOverall": false, "label": "Error_case14_ToUser", "isController": false}, {"data": [[1.646810895E12, 316.0]], "isOverall": false, "label": "Error_case16_ToUser", "isController": false}, {"data": [[1.646810925E12, 273.0]], "isOverall": false, "label": "Error_case2_UserWallets", "isController": false}, {"data": [[1.646810895E12, 275.0]], "isOverall": false, "label": "Error_case10_ToUser", "isController": false}, {"data": [[1.646810895E12, 445.0]], "isOverall": false, "label": "Error_case12_ToUser", "isController": false}, {"data": [[1.64681089E12, 287.0]], "isOverall": false, "label": "Error_case3_ToUser", "isController": false}, {"data": [[1.646810895E12, 281.0]], "isOverall": false, "label": "Error_case9_ToUser", "isController": false}, {"data": [[1.64681091E12, 289.0]], "isOverall": false, "label": "Error_case12_userwithdraw", "isController": false}, {"data": [[1.646810945E12, 899.0], [1.64681094E12, 1047.0]], "isOverall": false, "label": "Get Exchange Rate", "isController": false}, {"data": [[1.64681089E12, 335.0]], "isOverall": false, "label": "Error_case1_ToUser", "isController": false}, {"data": [[1.646810905E12, 356.0]], "isOverall": false, "label": "Error_case24_ToCompany", "isController": false}, {"data": [[1.64681093E12, 312.0]], "isOverall": false, "label": "Error_case1_tax", "isController": false}, {"data": [[1.646810945E12, 308.0]], "isOverall": false, "label": "Error_case5_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 336.0]], "isOverall": false, "label": "Error_case11_card", "isController": false}, {"data": [[1.646810945E12, 286.0]], "isOverall": false, "label": "Error_case1_Advanced profile", "isController": false}, {"data": [[1.646810935E12, 311.0]], "isOverall": false, "label": "Error_case10_tax", "isController": false}, {"data": [[1.64681095E12, 283.0]], "isOverall": false, "label": "Error_case23_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 353.0]], "isOverall": false, "label": "Error_case15_ToCompany", "isController": false}, {"data": [[1.646810915E12, 277.0]], "isOverall": false, "label": "Error_case3_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 351.0]], "isOverall": false, "label": "Error_case6_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 312.0]], "isOverall": false, "label": "Error_case9_companywithdraw", "isController": false}, {"data": [[1.646810905E12, 308.0]], "isOverall": false, "label": "Error_case25_ToCompany", "isController": false}, {"data": [[1.64681089E12, 276.0]], "isOverall": false, "label": "Error_case7_UpdateUser", "isController": false}, {"data": [[1.646810895E12, 310.0]], "isOverall": false, "label": "Error_case18_ToUser", "isController": false}, {"data": [[1.646810925E12, 275.0]], "isOverall": false, "label": "Error_case6_UserWallets", "isController": false}, {"data": [[1.64681095E12, 282.0]], "isOverall": false, "label": "Error_case13_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 278.0]], "isOverall": false, "label": "Error_case5_UserWallets", "isController": false}, {"data": [[1.64681092E12, 276.0]], "isOverall": false, "label": "Error_case13_card", "isController": false}, {"data": [[1.646810925E12, 270.0]], "isOverall": false, "label": "Error_case3_UserWallets", "isController": false}, {"data": [[1.64681088E12, 1581.0]], "isOverall": false, "label": "Key", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case4_UserWallets", "isController": false}, {"data": [[1.6468109E12, 322.0]], "isOverall": false, "label": "Error_case21_ToUser", "isController": false}, {"data": [[1.646810905E12, 404.0]], "isOverall": false, "label": "Error_case22_ToCompany", "isController": false}, {"data": [[1.646810905E12, 316.0]], "isOverall": false, "label": "Error_case14_ToCompany", "isController": false}, {"data": [[1.646810945E12, 337.0]], "isOverall": false, "label": "Error_case9_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 314.0]], "isOverall": false, "label": "Error_case15_CreateUser", "isController": false}, {"data": [[1.646810935E12, 299.75], [1.64681094E12, 348.5]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810945E12, 360.0]], "isOverall": false, "label": "Error_case6_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 395.0]], "isOverall": false, "label": "Error_case17_ToCompany", "isController": false}, {"data": [[1.64681091E12, 315.0]], "isOverall": false, "label": "Error_case8_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 303.0]], "isOverall": false, "label": "Error_case14_CreateUser", "isController": false}, {"data": [[1.64681095E12, 313.0]], "isOverall": false, "label": "Error_case22_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 320.0]], "isOverall": false, "label": "Error_case10_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 312.0]], "isOverall": false, "label": "Error_case8_tax", "isController": false}, {"data": [[1.64681091E12, 280.0]], "isOverall": false, "label": "Error_case2_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 323.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 303.0]], "isOverall": false, "label": "Error_case13_CreateUser", "isController": false}, {"data": [[1.6468109E12, 278.0]], "isOverall": false, "label": "Error_case9_ToCompany", "isController": false}, {"data": [[1.64681092E12, 274.0]], "isOverall": false, "label": "Error_case5_card", "isController": false}, {"data": [[1.64681089E12, 295.0]], "isOverall": false, "label": "Error_case8_UpdateUser", "isController": false}, {"data": [[1.64681095E12, 281.0]], "isOverall": false, "label": "Error_case26_Advanced profile", "isController": false}, {"data": [[1.646810945E12, 366.0]], "isOverall": false, "label": "Error_case4_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 332.0]], "isOverall": false, "label": "Error_case2_tax", "isController": false}, {"data": [[1.64681095E12, 280.0]], "isOverall": false, "label": "Error_case18_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 344.0]], "isOverall": false, "label": "Error_case10_CreateUser", "isController": false}, {"data": [[1.64681089E12, 308.0]], "isOverall": false, "label": "Error_case2_UpdateUser", "isController": false}, {"data": [[1.64681089E12, 275.0]], "isOverall": false, "label": "Error_case19_CreateUser", "isController": false}, {"data": [[1.646810935E12, 298.0]], "isOverall": false, "label": "Error_case3_States", "isController": false}, {"data": [[1.646810935E12, 278.0]], "isOverall": false, "label": "Error_case6_States", "isController": false}, {"data": [[1.64681092E12, 307.0]], "isOverall": false, "label": "Error_case9_card", "isController": false}, {"data": [[1.64681093E12, 344.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet", "isController": false}, {"data": [[1.64681093E12, 289.5]], "isOverall": false, "label": "Error_case1_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 361.0]], "isOverall": false, "label": "Error_case7_ToCompany", "isController": false}, {"data": [[1.64681093E12, 283.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case7_Programs", "isController": false}, {"data": [[1.646810915E12, 273.0]], "isOverall": false, "label": "Error_case11_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 313.0]], "isOverall": false, "label": "Error_case23_ToUser", "isController": false}, {"data": [[1.646810925E12, 285.0]], "isOverall": false, "label": "Error_case16_card", "isController": false}, {"data": [[1.646810885E12, 279.0]], "isOverall": false, "label": "Error_case4_CreateUser", "isController": false}, {"data": [[1.646810945E12, 290.0]], "isOverall": false, "label": "Error_case12_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case4_Programs", "isController": false}, {"data": [[1.64681095E12, 291.0]], "isOverall": false, "label": "Error_case20_Advanced profile", "isController": false}, {"data": [[1.6468109E12, 275.0]], "isOverall": false, "label": "Error_case8_ToCompany", "isController": false}, {"data": [[1.64681092E12, 344.0]], "isOverall": false, "label": "Error_case3_card", "isController": false}, {"data": [[1.646810925E12, 280.0]], "isOverall": false, "label": "Error_case1_Programs", "isController": false}, {"data": [[1.64681089E12, 277.0]], "isOverall": false, "label": "Error_case6_UpdateUser", "isController": false}, {"data": [[1.64681091E12, 332.0]], "isOverall": false, "label": "Error_case7_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 331.0]], "isOverall": false, "label": "Error_case15_card", "isController": false}, {"data": [[1.6468109E12, 280.0]], "isOverall": false, "label": "Error_case2_ToCompany", "isController": false}, {"data": [[1.64681093E12, 272.0]], "isOverall": false, "label": "Error_case5_CompanyWallets", "isController": false}, {"data": [[1.64681095E12, 281.0]], "isOverall": false, "label": "Error_case15_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 279.0]], "isOverall": false, "label": "Error_case3_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 302.0]], "isOverall": false, "label": "Error_case6_CreateUser", "isController": false}, {"data": [[1.64681091E12, 326.0]], "isOverall": false, "label": "Error_case11_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 274.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet", "isController": false}, {"data": [[1.646810915E12, 316.0]], "isOverall": false, "label": "Error_case4_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 310.0]], "isOverall": false, "label": "Error_case1_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 319.0]], "isOverall": false, "label": "Error_case17_ToUser", "isController": false}, {"data": [[1.646810905E12, 355.0]], "isOverall": false, "label": "Error_case23_ToCompany", "isController": false}, {"data": [[1.64681089E12, 441.0]], "isOverall": false, "label": "Error_case4_UpdateUser", "isController": false}, {"data": [[1.6468109E12, 273.0]], "isOverall": false, "label": "Error_case10_ToCompany", "isController": false}, {"data": [[1.646810915E12, 321.0]], "isOverall": false, "label": "Error_case7_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 311.0]], "isOverall": false, "label": "Error_case20_ToUser", "isController": false}, {"data": [[1.646810935E12, 310.0]], "isOverall": false, "label": "Error_case7_tax", "isController": false}, {"data": [[1.646810895E12, 279.0]], "isOverall": false, "label": "Error_case13_ToUser", "isController": false}, {"data": [[1.6468109E12, 272.0]], "isOverall": false, "label": "Error_case3_ToCompany", "isController": false}, {"data": [[1.646810915E12, 356.0]], "isOverall": false, "label": "Error_case10_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 279.0]], "isOverall": false, "label": "Error_case6_ToUser", "isController": false}, {"data": [[1.646810895E12, 274.0]], "isOverall": false, "label": "Error_case8_ToUser", "isController": false}, {"data": [[1.646810895E12, 314.0]], "isOverall": false, "label": "Error_case15_ToUser", "isController": false}, {"data": [[1.646810915E12, 532.0], [1.64681092E12, 278.0]], "isOverall": false, "label": "ValidateIBAN", "isController": false}, {"data": [[1.64681095E12, 284.0]], "isOverall": false, "label": "Error_case25_Advanced profile", "isController": false}, {"data": [[1.646810895E12, 274.0]], "isOverall": false, "label": "Error_case11_ToUser", "isController": false}, {"data": [[1.646810935E12, 356.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax", "isController": false}, {"data": [[1.64681089E12, 339.0]], "isOverall": false, "label": "Error_case2_ToUser", "isController": false}, {"data": [[1.646810895E12, 276.0]], "isOverall": false, "label": "Error_case4_ToUser", "isController": false}, {"data": [[1.64681092E12, 279.0]], "isOverall": false, "label": "Error_case6_card", "isController": false}, {"data": [[1.646810915E12, 274.0]], "isOverall": false, "label": "Error_case13_companywithdraw", "isController": false}, {"data": [[1.64681095E12, 298.0]], "isOverall": false, "label": "Error_case19_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 470.0]], "isOverall": false, "label": "Error_case11_CreateUser", "isController": false}, {"data": [[1.646810885E12, 291.0]], "isOverall": false, "label": "Error_case2_CreateUser", "isController": false}, {"data": [[1.64681089E12, 281.0]], "isOverall": false, "label": "Error_case18_CreateUser", "isController": false}, {"data": [[1.6468109E12, 273.0]], "isOverall": false, "label": "Error_case4_ToCompany", "isController": false}, {"data": [[1.646810945E12, 538.0]], "isOverall": false, "label": "Error_case10_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 274.0]], "isOverall": false, "label": "Error_case4_card", "isController": false}, {"data": [[1.646810935E12, 273.0]], "isOverall": false, "label": "Error_case3_tax", "isController": false}, {"data": [[1.64681089E12, 279.0]], "isOverall": false, "label": "Error_case17_CreateUser", "isController": false}, {"data": [[1.646810945E12, 700.0]], "isOverall": false, "label": "Error_case7_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 331.0]], "isOverall": false, "label": "Error_case8_CreateUser", "isController": false}, {"data": [[1.64681092E12, 277.0]], "isOverall": false, "label": "Error_case10_card", "isController": false}, {"data": [[1.64681095E12, 307.0]], "isOverall": false, "label": "Error_case21_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 278.0]], "isOverall": false, "label": "Error_case1_CreateUser", "isController": false}, {"data": [[1.6468109E12, 481.0]], "isOverall": false, "label": "Error_case22_ToUser", "isController": false}, {"data": [[1.64681091E12, 310.0]], "isOverall": false, "label": "Error_case2_companywithdraw", "isController": false}, {"data": [[1.64681092E12, 275.0]], "isOverall": false, "label": "Error_case8_card", "isController": false}, {"data": [[1.64681092E12, 307.0]], "isOverall": false, "label": "Error_case2_card", "isController": false}, {"data": [[1.6468109E12, 351.0]], "isOverall": false, "label": "Error_case1_ToCompany", "isController": false}, {"data": [[1.646810885E12, 330.0]], "isOverall": false, "label": "Error_case12_CreateUser", "isController": false}, {"data": [[1.646810945E12, 285.0]], "isOverall": false, "label": "Error_case11_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 301.0]], "isOverall": false, "label": "Error_case6_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 327.0]], "isOverall": false, "label": "Error_case11_ToCompany", "isController": false}, {"data": [[1.646810915E12, 417.0]], "isOverall": false, "label": "Error_case8_companywithdraw", "isController": false}, {"data": [[1.64681089E12, 283.0]], "isOverall": false, "label": "Error_case9_UpdateUser", "isController": false}, {"data": [[1.64681092E12, 291.0]], "isOverall": false, "label": "Error_case14_card", "isController": false}, {"data": [[1.646810915E12, 320.0]], "isOverall": false, "label": "Error_case5_companywithdraw", "isController": false}, {"data": [[1.64681093E12, 283.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet", "isController": false}, {"data": [[1.64681095E12, 286.0]], "isOverall": false, "label": "Error_case14_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 312.0]], "isOverall": false, "label": "Error_case4_userwithdraw", "isController": false}, {"data": [[1.64681091E12, 355.0]], "isOverall": false, "label": "Error_case6_userwithdraw", "isController": false}, {"data": [[1.6468109E12, 274.0]], "isOverall": false, "label": "Error_case6_ToCompany", "isController": false}, {"data": [[1.646810925E12, 286.0]], "isOverall": false, "label": "Error_case20_card", "isController": false}, {"data": [[1.646810895E12, 350.0]], "isOverall": false, "label": "Error_case19_ToUser", "isController": false}, {"data": [[1.64681093E12, 280.0]], "isOverall": false, "label": "Error_case4_CompanyWallets", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case5_Programs", "isController": false}, {"data": [[1.646810945E12, 284.0]], "isOverall": false, "label": "Error_case3_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 303.0]], "isOverall": false, "label": "Error_case16_CreateUser", "isController": false}, {"data": [[1.64681093E12, 291.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet", "isController": false}, {"data": [[1.64681089E12, 313.0]], "isOverall": false, "label": "Error_case5_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 344.0]], "isOverall": false, "label": "Error_case20_ToCompany", "isController": false}, {"data": [[1.646810885E12, 310.0]], "isOverall": false, "label": "Error_case3_CreateUser", "isController": false}, {"data": [[1.646810935E12, 292.0]], "isOverall": false, "label": "Error_case1_States", "isController": false}, {"data": [[1.646810935E12, 278.0]], "isOverall": false, "label": "Error_case2_States", "isController": false}, {"data": [[1.64681095E12, 296.0]], "isOverall": false, "label": "Error_case24_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 273.0]], "isOverall": false, "label": "Error_case17_card", "isController": false}, {"data": [[1.646810885E12, 304.0]], "isOverall": false, "label": "Error_case9_CreateUser", "isController": false}, {"data": [[1.646810905E12, 442.0]], "isOverall": false, "label": "Error_case21_ToCompany", "isController": false}, {"data": [[1.646810935E12, 332.0]], "isOverall": false, "label": "Error_case4_tax", "isController": false}, {"data": [[1.646810925E12, 276.0]], "isOverall": false, "label": "Error_case3_Programs", "isController": false}, {"data": [[1.64681092E12, 295.0]], "isOverall": false, "label": "Error_case1_card", "isController": false}, {"data": [[1.646810905E12, 346.0]], "isOverall": false, "label": "Error_case19_ToCompany", "isController": false}, {"data": [[1.646810945E12, 284.0]], "isOverall": false, "label": "Error_case2_Advanced profile", "isController": false}, {"data": [[1.64681095E12, 283.0]], "isOverall": false, "label": "Error_case16_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 271.0]], "isOverall": false, "label": "Error_case21_card", "isController": false}, {"data": [[1.646810905E12, 290.0]], "isOverall": false, "label": "Error_case12_ToCompany", "isController": false}, {"data": [[1.646810925E12, 274.0]], "isOverall": false, "label": "Error_case19_card", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case2_Programs", "isController": false}, {"data": [[1.646810945E12, 317.0]], "isOverall": false, "label": "Error_case8_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 272.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 335.0]], "isOverall": false, "label": "Error_case7_CreateUser", "isController": false}, {"data": [[1.646810905E12, 358.0]], "isOverall": false, "label": "Error_case18_ToCompany", "isController": false}, {"data": [[1.6468109E12, 275.0]], "isOverall": false, "label": "Error_case5_ToCompany", "isController": false}, {"data": [[1.64681089E12, 338.0]], "isOverall": false, "label": "Error_case1_UpdateUser", "isController": false}, {"data": [[1.646810915E12, 273.0]], "isOverall": false, "label": "Error_case12_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 357.0]], "isOverall": false, "label": "Error_case13_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 272.0]], "isOverall": false, "label": "Error_case3_CompanyWallets", "isController": false}, {"data": [[1.64681092E12, 277.0]], "isOverall": false, "label": "Error_case7_card", "isController": false}, {"data": [[1.646810935E12, 308.0]], "isOverall": false, "label": "Error_case5_tax", "isController": false}, {"data": [[1.646810925E12, 272.0]], "isOverall": false, "label": "Error_case6_Programs", "isController": false}, {"data": [[1.64681091E12, 274.0]], "isOverall": false, "label": "Error_case9_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 329.0]], "isOverall": false, "label": "Error_case9_tax", "isController": false}, {"data": [[1.646810925E12, 329.0]], "isOverall": false, "label": "Error_case18_card", "isController": false}, {"data": [[1.64681089E12, 274.0]], "isOverall": false, "label": "Error_case3_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 331.0]], "isOverall": false, "label": "Error_case13_ToCompany", "isController": false}, {"data": [[1.64681091E12, 331.0]], "isOverall": false, "label": "Error_case5_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 392.0]], "isOverall": false, "label": "Error_case12_card", "isController": false}, {"data": [[1.64681094E12, 315.33333333333337]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810885E12, 276.0]], "isOverall": false, "label": "Error_case5_CreateUser", "isController": false}, {"data": [[1.6468109E12, 277.0]], "isOverall": false, "label": "Error_case24_ToUser", "isController": false}, {"data": [[1.64681091E12, 315.0]], "isOverall": false, "label": "Error_case1_userwithdraw", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 5000, "maxX": 1.64681095E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 0.0, "minX": 1.64681088E12, "maxY": 639.0, "series": [{"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case16_ToCompany", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case1_UserWallets", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case5_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case7_ToUser", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case17_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case2_CompanyWallets", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case14_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case16_ToUser", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case2_UserWallets", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case10_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case12_ToUser", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case3_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case9_ToUser", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case12_userwithdraw", "isController": false}, {"data": [[1.646810945E12, 0.0], [1.64681094E12, 110.0]], "isOverall": false, "label": "Get Exchange Rate", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case1_ToUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case24_ToCompany", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case1_tax", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case5_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case11_card", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case1_Advanced profile", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case10_tax", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case23_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case15_ToCompany", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case3_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case6_companywithdraw", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case9_companywithdraw", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case25_ToCompany", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case7_UpdateUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case18_ToUser", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case6_UserWallets", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case13_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case5_UserWallets", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case13_card", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case3_UserWallets", "isController": false}, {"data": [[1.64681088E12, 639.0]], "isOverall": false, "label": "Key", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case4_UserWallets", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case21_ToUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case22_ToCompany", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case14_ToCompany", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case9_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case15_CreateUser", "isController": false}, {"data": [[1.646810935E12, 0.0], [1.64681094E12, 0.0]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case6_Advanced profile", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case17_ToCompany", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case8_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case14_CreateUser", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case22_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case10_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case8_tax", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case2_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case13_CreateUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case9_ToCompany", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case5_card", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case8_UpdateUser", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case26_Advanced profile", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case4_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case2_tax", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case18_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case10_CreateUser", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case2_UpdateUser", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case19_CreateUser", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case3_States", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case6_States", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case9_card", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case1_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case7_ToCompany", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case7_Programs", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case11_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case23_ToUser", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case16_card", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case4_CreateUser", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case12_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case4_Programs", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case20_Advanced profile", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case8_ToCompany", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case3_card", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case1_Programs", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case6_UpdateUser", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case7_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case15_card", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case2_ToCompany", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case5_CompanyWallets", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case15_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case3_userwithdraw", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case6_CreateUser", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case11_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case4_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case1_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case17_ToUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case23_ToCompany", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case4_UpdateUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case10_ToCompany", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case7_companywithdraw", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case20_ToUser", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case7_tax", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case13_ToUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case3_ToCompany", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case10_companywithdraw", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case6_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case8_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case15_ToUser", "isController": false}, {"data": [[1.646810915E12, 0.0], [1.64681092E12, 0.0]], "isOverall": false, "label": "ValidateIBAN", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case25_Advanced profile", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case11_ToUser", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case6OtherthanUS_tax", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case2_ToUser", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case4_ToUser", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case6_card", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case13_companywithdraw", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case19_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case11_CreateUser", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case2_CreateUser", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case18_CreateUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case4_ToCompany", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case10_Advanced profile", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case4_card", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case3_tax", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case17_CreateUser", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case7_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case8_CreateUser", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case10_card", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case21_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case1_CreateUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case22_ToUser", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case2_companywithdraw", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case8_card", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case2_card", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case1_ToCompany", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case12_CreateUser", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case11_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case6_CompanyWallets", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case11_ToCompany", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case8_companywithdraw", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case9_UpdateUser", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case14_card", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case5_companywithdraw", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case14_Advanced profile", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case4_userwithdraw", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case6_userwithdraw", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case6_ToCompany", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case20_card", "isController": false}, {"data": [[1.646810895E12, 0.0]], "isOverall": false, "label": "Error_case19_ToUser", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case4_CompanyWallets", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case5_Programs", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case3_Advanced profile", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case16_CreateUser", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case5_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case20_ToCompany", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case3_CreateUser", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case1_States", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case2_States", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case24_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case17_card", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case9_CreateUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case21_ToCompany", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case4_tax", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case3_Programs", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case1_card", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case19_ToCompany", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case2_Advanced profile", "isController": false}, {"data": [[1.64681095E12, 0.0]], "isOverall": false, "label": "Error_case16_Advanced profile", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case21_card", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case12_ToCompany", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case19_card", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case2_Programs", "isController": false}, {"data": [[1.646810945E12, 0.0]], "isOverall": false, "label": "Error_case8_Advanced profile", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case7_CreateUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case18_ToCompany", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case5_ToCompany", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case1_UpdateUser", "isController": false}, {"data": [[1.646810915E12, 0.0]], "isOverall": false, "label": "Error_case12_companywithdraw", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case13_userwithdraw", "isController": false}, {"data": [[1.64681093E12, 0.0]], "isOverall": false, "label": "Error_case3_CompanyWallets", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case7_card", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case5_tax", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case6_Programs", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case9_userwithdraw", "isController": false}, {"data": [[1.646810935E12, 0.0]], "isOverall": false, "label": "Error_case9_tax", "isController": false}, {"data": [[1.646810925E12, 0.0]], "isOverall": false, "label": "Error_case18_card", "isController": false}, {"data": [[1.64681089E12, 0.0]], "isOverall": false, "label": "Error_case3_UpdateUser", "isController": false}, {"data": [[1.646810905E12, 0.0]], "isOverall": false, "label": "Error_case13_ToCompany", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case5_userwithdraw", "isController": false}, {"data": [[1.64681092E12, 0.0]], "isOverall": false, "label": "Error_case12_card", "isController": false}, {"data": [[1.64681094E12, 0.0]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter", "isController": false}, {"data": [[1.646810885E12, 0.0]], "isOverall": false, "label": "Error_case5_CreateUser", "isController": false}, {"data": [[1.6468109E12, 0.0]], "isOverall": false, "label": "Error_case24_ToUser", "isController": false}, {"data": [[1.64681091E12, 0.0]], "isOverall": false, "label": "Error_case1_userwithdraw", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 5000, "maxX": 1.64681095E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 271.0, "minX": 1.64681088E12, "maxY": 1586.0, "series": [{"data": [[1.646810935E12, 356.0], [1.646810915E12, 1047.0], [1.646810895E12, 445.0], [1.64681094E12, 418.0], [1.64681092E12, 392.0], [1.6468109E12, 481.0], [1.64681088E12, 1586.0], [1.646810945E12, 700.0], [1.646810925E12, 329.0], [1.646810905E12, 461.0], [1.646810885E12, 470.0], [1.64681095E12, 313.0], [1.64681093E12, 344.0], [1.64681091E12, 357.0], [1.64681089E12, 339.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.646810935E12, 344.0], [1.646810915E12, 1047.0], [1.646810895E12, 378.50000000000006], [1.64681094E12, 418.0], [1.64681092E12, 373.2], [1.6468109E12, 397.0000000000001], [1.64681088E12, 1586.0], [1.646810945E12, 651.4000000000002], [1.646810925E12, 290.30000000000007], [1.646810905E12, 451.5], [1.646810885E12, 432.20000000000016], [1.64681095E12, 310.0], [1.64681093E12, 336.3], [1.64681091E12, 356.6], [1.64681089E12, 339.0]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.646810935E12, 356.0], [1.646810915E12, 1047.0], [1.646810895E12, 445.0], [1.64681094E12, 418.0], [1.64681092E12, 392.0], [1.6468109E12, 481.0], [1.64681088E12, 1586.0], [1.646810945E12, 700.0], [1.646810925E12, 329.0], [1.646810905E12, 461.0], [1.646810885E12, 470.0], [1.64681095E12, 313.0], [1.64681093E12, 344.0], [1.64681091E12, 357.0], [1.64681089E12, 339.0]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.646810935E12, 356.0], [1.646810915E12, 1047.0], [1.646810895E12, 445.0], [1.64681094E12, 418.0], [1.64681092E12, 392.0], [1.6468109E12, 481.0], [1.64681088E12, 1586.0], [1.646810945E12, 700.0], [1.646810925E12, 329.0], [1.646810905E12, 461.0], [1.646810885E12, 470.0], [1.64681095E12, 313.0], [1.64681093E12, 344.0], [1.64681091E12, 357.0], [1.64681089E12, 339.0]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.646810935E12, 273.0], [1.646810915E12, 273.0], [1.646810895E12, 274.0], [1.64681094E12, 280.0], [1.64681092E12, 274.0], [1.6468109E12, 273.0], [1.64681088E12, 1586.0], [1.646810945E12, 285.0], [1.646810925E12, 271.0], [1.646810905E12, 290.0], [1.646810885E12, 276.0], [1.64681095E12, 281.0], [1.64681093E12, 272.0], [1.64681091E12, 274.0], [1.64681089E12, 276.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.646810935E12, 309.0], [1.646810915E12, 274.5], [1.646810895E12, 282.5], [1.64681094E12, 341.0], [1.64681092E12, 291.0], [1.6468109E12, 279.5], [1.64681088E12, 1586.0], [1.646810945E12, 312.5], [1.646810925E12, 274.0], [1.646810905E12, 354.0], [1.646810885E12, 304.0], [1.64681095E12, 285.0], [1.64681093E12, 283.0], [1.64681091E12, 316.0], [1.64681089E12, 284.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 5000, "maxX": 1.64681095E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 277.0, "minX": 1.0, "maxY": 1047.5, "series": [{"data": [[1.0, 973.5], [4.0, 281.0], [2.0, 342.0], [3.0, 307.0]], "isOverall": false, "label": "Successes", "isController": false}, {"data": [[4.0, 303.0], [2.0, 277.0], [1.0, 1047.5], [3.0, 310.0]], "isOverall": false, "label": "Failures", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 4.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 277.0, "minX": 1.0, "maxY": 1047.0, "series": [{"data": [[1.0, 970.5], [4.0, 281.0], [2.0, 341.5], [3.0, 307.0]], "isOverall": false, "label": "Successes", "isController": false}, {"data": [[4.0, 303.0], [2.0, 277.0], [1.0, 1047.0], [3.0, 310.0]], "isOverall": false, "label": "Failures", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 4.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 0.4, "minX": 1.64681088E12, "maxY": 3.6, "series": [{"data": [[1.646810935E12, 3.2], [1.646810915E12, 2.8], [1.646810895E12, 3.2], [1.64681094E12, 2.0], [1.64681092E12, 3.2], [1.6468109E12, 3.2], [1.64681088E12, 0.4], [1.646810945E12, 2.6], [1.646810925E12, 3.6], [1.646810905E12, 2.6], [1.646810885E12, 3.2], [1.64681095E12, 2.6], [1.64681093E12, 3.4], [1.64681091E12, 3.2], [1.64681089E12, 3.2]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 5000, "maxX": 1.64681095E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.64681088E12, "maxY": 3.6, "series": [{"data": [[1.646810935E12, 2.8], [1.646810915E12, 1.2], [1.646810895E12, 3.2], [1.64681094E12, 0.8], [1.64681092E12, 2.6], [1.6468109E12, 3.2], [1.64681088E12, 0.2], [1.646810945E12, 2.4], [1.646810925E12, 3.6], [1.646810905E12, 2.8], [1.646810885E12, 2.4], [1.64681095E12, 2.8], [1.64681093E12, 3.2], [1.64681091E12, 2.6], [1.64681089E12, 1.2]], "isOverall": false, "label": "200", "isController": false}, {"data": [[1.646810935E12, 0.4], [1.646810915E12, 1.6], [1.646810885E12, 0.8], [1.64681094E12, 0.8], [1.64681092E12, 0.6], [1.64681093E12, 0.2], [1.64681091E12, 0.4], [1.64681089E12, 1.6]], "isOverall": false, "label": "400", "isController": false}, {"data": [[1.64681089E12, 0.4]], "isOverall": false, "label": "401", "isController": false}, {"data": [[1.646810945E12, 0.2], [1.64681094E12, 0.4]], "isOverall": false, "label": "500", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 5000, "maxX": 1.64681095E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.64681088E12, "maxY": 0.8, "series": [{"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case20_ToUser-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case20_ToCompany-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case2_Programs-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case18_ToCompany-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case5_companywithdraw-failure", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case5_tax-success", "isController": false}, {"data": [[1.64681094E12, 0.8]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter-failure", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case8_card-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case9_ToUser-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case4_tax-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case3_tax-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case9_Advanced profile-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case21_ToCompany-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case2_tax-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case1_tax-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case6_States-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case6_BeneficiaryWallet-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case14_ToUser-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case2_UpdateUser-failure", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case7_UpdateUser-failure", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case10_userwithdraw-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case19_ToCompany-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case8_tax-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case15_ToUser-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case7_tax-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case3_ToUser-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case1_UserWallets-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case9_tax-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case11_userwithdraw-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case7_Programs-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case3_UserWallets-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case2_ToUser-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case21_Advanced profile-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case3_Advanced profile-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case1_Advanced profile-success", "isController": false}, {"data": [[1.646810945E12, 0.2], [1.64681094E12, 0.4]], "isOverall": false, "label": "Get Exchange Rate-failure", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case18_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case21_ToUser-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case12_card-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case18_card-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case4_Advanced profile-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case3_companywithdraw-failure", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case24_Advanced profile-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case12_Advanced profile-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case12_userwithdraw-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case1_Programs-success", "isController": false}, {"data": [[1.64681093E12, 0.4]], "isOverall": false, "label": "Error_case1_CompanyWallets-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case15_Advanced profile-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case12_CreateUser-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case17_CreateUser-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case6_Advanced profile-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case16_ToCompany-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case8_companywithdraw-failure", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case3_CompanyWallets-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case2_card-success", "isController": false}, {"data": [[1.646810935E12, 0.4]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter-failure", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case6_card-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case1_ToUser-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case2_userwithdraw-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case5_Programs-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case1_ToCompany-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case24_ToCompany-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case14_ToCompany-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case4_ToUser-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case6OtherthanUS_tax-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case13_ToCompany-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case23_ToCompany-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case20_card-success", "isController": false}, {"data": [[1.646810935E12, 0.4], [1.64681094E12, 0.4]], "isOverall": false, "label": "Fetch User Wallet Transactions_withFilter-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case5_CompanyWallets-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case10_ToCompany-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case11_companywithdraw-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case9_userwithdraw-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case9_card-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case1_companywithdraw-failure", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case4_ToCompany-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case6_companywithdraw-failure", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case5_card-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case13_card-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case8_ToUser-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case17_ToCompany-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case17_card-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case22_ToUser-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case18_ToUser-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case4_BeneficiaryWallet-success", "isController": false}, {"data": [[1.64681094E12, 0.4]], "isOverall": false, "label": "Fetch Company Wallet Transactions_withFilter-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case8_ToCompany-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case11_ToUser-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case6_ToUser-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case3_States-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case6_userwithdraw-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case20_Advanced profile-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case4_card-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case4_Programs-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case19_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case7_ToCompany-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case2_Advanced profile-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case3_UpdateUser-failure", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case7_userwithdraw-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case6_UpdateUser-failure", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case10_card-failure", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case13_companywithdraw-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case1_card-failure", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case4_companywithdraw-failure", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case6_ToCompany-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case2_States-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case12_ToUser-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case17_ToUser-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case16_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case5_ToCompany-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case9_companywithdraw-failure", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case9_UpdateUser-failure", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case4_userwithdraw-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case2_UserWallets-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case5_ToUser-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case23_Advanced profile-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case15_CreateUser-failure", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case19_ToUser-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case14_card-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case16_CreateUser-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case11_Advanced profile-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case25_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case11_ToCompany-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case5_Advanced profile-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case2_CompanyWallets-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case13_Advanced profile-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case4_UserWallets-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case5_userwithdraw-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case14_CreateUser-failure", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case16_card-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case8_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case3_ToCompany-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case7_ToUser-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case18_CreateUser-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case19_CreateUser-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case7_Advanced profile-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case23_ToUser-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case26_Advanced profile-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case10_ToUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case13_CreateUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case11_CreateUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case10_CreateUser-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case14_Advanced profile-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case5_UserWallets-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case17_Advanced profile-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case1_CreateUser-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case3_Programs-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case2_ToCompany-success", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case13_ToUser-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case5_BeneficiaryWallet-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case12_ToCompany-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case1_States-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case25_ToCompany-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case8_UpdateUser-failure", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case10_companywithdraw-failure", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case2_companywithdraw-failure", "isController": false}, {"data": [[1.64681089E12, 0.4]], "isOverall": false, "label": "Error_case1_UpdateUser-failure", "isController": false}, {"data": [[1.646810895E12, 0.2]], "isOverall": false, "label": "Error_case16_ToUser-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case13_userwithdraw-success", "isController": false}, {"data": [[1.646810945E12, 0.2]], "isOverall": false, "label": "Error_case10_Advanced profile-success", "isController": false}, {"data": [[1.64681095E12, 0.2]], "isOverall": false, "label": "Error_case22_Advanced profile-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case5_UpdateUser-failure", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case4_CompanyWallets-success", "isController": false}, {"data": [[1.64681089E12, 0.2]], "isOverall": false, "label": "Error_case4_UpdateUser-failure", "isController": false}, {"data": [[1.646810915E12, 0.6], [1.64681092E12, 0.2]], "isOverall": false, "label": "ValidateIBAN-success", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case7_companywithdraw-failure", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case1_BeneficiaryWallet-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case7_BeneficiaryWallet-failure", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case3_userwithdraw-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case6_UserWallets-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case21_card-success", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case15_ToCompany-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case24_ToUser-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case7_card-failure", "isController": false}, {"data": [[1.646810905E12, 0.2]], "isOverall": false, "label": "Error_case22_ToCompany-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case3_card-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case11_card-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case9_CreateUser-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case2_BeneficiaryWallet-success", "isController": false}, {"data": [[1.64681092E12, 0.2]], "isOverall": false, "label": "Error_case15_card-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case8_CreateUser-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case6_CompanyWallets-success", "isController": false}, {"data": [[1.64681088E12, 0.2]], "isOverall": false, "label": "Key-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case6_CreateUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case7_CreateUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case5_CreateUser-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case4_CreateUser-success", "isController": false}, {"data": [[1.64681093E12, 0.2]], "isOverall": false, "label": "Error_case3_BeneficiaryWallet-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case3_CreateUser-failure", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case6_Programs-success", "isController": false}, {"data": [[1.6468109E12, 0.2]], "isOverall": false, "label": "Error_case9_ToCompany-success", "isController": false}, {"data": [[1.646810935E12, 0.2]], "isOverall": false, "label": "Error_case10_tax-success", "isController": false}, {"data": [[1.646810925E12, 0.2]], "isOverall": false, "label": "Error_case19_card-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case1_userwithdraw-success", "isController": false}, {"data": [[1.64681091E12, 0.2]], "isOverall": false, "label": "Error_case8_userwithdraw-success", "isController": false}, {"data": [[1.646810885E12, 0.2]], "isOverall": false, "label": "Error_case2_CreateUser-failure", "isController": false}, {"data": [[1.646810915E12, 0.2]], "isOverall": false, "label": "Error_case12_companywithdraw-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 5000, "maxX": 1.64681095E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 0.2, "minX": 1.64681088E12, "maxY": 3.6, "series": [{"data": [[1.646810935E12, 2.8], [1.646810915E12, 1.2], [1.646810895E12, 3.2], [1.64681094E12, 0.8], [1.64681092E12, 2.6], [1.6468109E12, 3.2], [1.64681088E12, 0.2], [1.646810945E12, 2.4], [1.646810925E12, 3.6], [1.646810905E12, 2.8], [1.646810885E12, 2.4], [1.64681095E12, 2.8], [1.64681093E12, 3.2], [1.64681091E12, 2.6], [1.64681089E12, 1.2]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [[1.646810945E12, 0.2], [1.646810935E12, 0.4], [1.646810915E12, 1.6], [1.646810885E12, 0.8], [1.64681094E12, 1.2], [1.64681092E12, 0.6], [1.64681093E12, 0.2], [1.64681091E12, 0.4], [1.64681089E12, 2.0]], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 5000, "maxX": 1.64681095E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

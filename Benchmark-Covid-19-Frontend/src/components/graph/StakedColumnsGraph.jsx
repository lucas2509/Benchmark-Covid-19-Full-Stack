import Chart from 'react-apexcharts'

export default function StakedColumnsGraph({resultsSumary, benchmarkData}){
    const options = {
        chart: {
            type: 'bar',
            height: 350,
            stacked: true,
          },
          stroke: {
            width: 1,
            colors: ['#fff']
          },
          title: {
            text: "Dados Resumidos",
            align: 'center',
            style: {
                fontSize:  '36px',
                fontWeight:  'bold',
                color:  '#263238'
              }
          },
          plotOptions: {
            bar: {
              horizontal: false
            }
          },
          xaxis: {
            categories: [
              'Data inicial',
              'Data final'
            ]
          },yaxis: {
            logarithmic: false,
            logBase: 10,
        },
          fill: {
            opacity: 10
          },
          colors: ['#4682B4', '#4682B4', '#80f1cb', '#00E396'],
         
          legend: {
            position: 'top',
            horizontalAlign: 'left',
            fontSize: '14px'
          },
    }

    const series = [
        {
          name: 'Números de mortos: '+benchmarkData.place_name_1,
          group: 'budget',
          data: [resultsSumary[0], resultsSumary[1]]
        },
        {
          name: 'Números de mortos - '+benchmarkData.place_name_2,
          group: 'actual',
          data: [resultsSumary[2], resultsSumary[3]]
        },
        {
          name: 'Números de casos: - '+benchmarkData.place_name_1,
          group: 'budget',
          data: [resultsSumary[4], resultsSumary[5]]
        },
        {
          name: 'Números de casos: - '+benchmarkData.place_name_2,
          group: 'actual',
          data: [resultsSumary[6], resultsSumary[7]]
        }
      ]

    return <Chart options={options} series={series} type={"bar"}/>
}
import Chart from 'react-apexcharts';

//Componente de gráfico do tipo barra
export default function BarGraph({title,series,categorias}){
    var options = {
        chart: {
          type: 'bar',
          height: 350
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%',
            endingShape: 'rounded'
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 1,
          colors: ['transparent']
        },
        xaxis: {
          categories: categorias,
        },
        fill: {
          opacity: 1
        },
        title: {
            text: title,
            align: 'center',
            style: {
                fontSize:  '36px',
                fontWeight:  'bold',
                color:  '#263238'
              }
          },
          yaxis: {
            logarithmic: true
          }
      }


    return <Chart options={options} series={series} type={"bar"}/>;
}